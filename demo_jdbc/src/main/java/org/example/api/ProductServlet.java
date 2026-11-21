package org.example.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.dto.ProductDTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private boolean isAdmin(HttpServletRequest req) {
        return "admin".equals(req.getSession().getAttribute("role"));
    }

    private ProductDTO readProduct(Connection conn, ResultSet rs) throws SQLException {
        ProductDTO p = new ProductDTO();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setSoluong(rs.getInt("soluong"));

        String imageSql = "SELECT image_url FROM product_images WHERE product_id=? ORDER BY id";
        try (PreparedStatement ps = conn.prepareStatement(imageSql)) {
            ps.setInt(1, p.getId());
            try (ResultSet images = ps.executeQuery()) {
                while (images.next()) p.getImages().add(images.getString("image_url"));
            }
        }
        return p;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        try (Connection conn = DBConnection.getConnection()) {
            if ("add".equals(action)) {
                if (!isAdmin(req)) { resp.sendError(403); return; }
                req.getRequestDispatcher("/addProduct.jsp").forward(req, resp);
                return;
            }

            if ("edit".equals(action)) {
                if (!isAdmin(req)) { resp.sendError(403); return; }
                int id = Integer.parseInt(req.getParameter("id"));
                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE id=?")) {
                    ps.setInt(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) { resp.sendRedirect(req.getContextPath()+"/product"); return; }
                        ProductDTO p = readProduct(conn, rs);
                        req.setAttribute("product", p);
                    }
                }
                req.getRequestDispatcher("/editProduct.jsp").forward(req, resp);
                return;
            }

            if ("detail".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE id=?")) {
                    ps.setInt(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) { resp.sendRedirect(req.getContextPath()+"/product"); return; }
                        req.setAttribute("product", readProduct(conn, rs));
                    }
                }
                req.getRequestDispatcher("/productDetail.jsp").forward(req, resp);
                return;
            }

            if ("delete".equals(action)) {
                if (!isAdmin(req)) { resp.sendError(403); return; }
                int id = Integer.parseInt(req.getParameter("id"));
                try (PreparedStatement ps = conn.prepareStatement("DELETE FROM product WHERE id=?")) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }
                resp.sendRedirect(req.getContextPath()+"/product");
                return;
            }

            String search = req.getParameter("search");
            String min = req.getParameter("minPrice");
            String max = req.getParameter("maxPrice");
            String sort = req.getParameter("sort");

            String sql = "SELECT * FROM product WHERE 1=1";
            if (search != null && !search.trim().isEmpty()) sql += " AND name LIKE ?";
            if (min != null && !min.trim().isEmpty()) sql += " AND price >= ?";
            if (max != null && !max.trim().isEmpty()) sql += " AND price <= ?";
            if ("asc".equals(sort)) sql += " ORDER BY price ASC";
            else if ("desc".equals(sort)) sql += " ORDER BY price DESC";

            List<ProductDTO> products = new ArrayList<>();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                int i = 1;
                if (search != null && !search.trim().isEmpty()) ps.setString(i++, "%"+search.trim()+"%");
                if (min != null && !min.trim().isEmpty()) ps.setBigDecimal(i++, new BigDecimal(min));
                if (max != null && !max.trim().isEmpty()) ps.setBigDecimal(i++, new BigDecimal(max));
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) products.add(readProduct(conn, rs));
                }
            }
            req.setAttribute("products", products);
            req.getRequestDispatcher("/product.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Giá hoặc mã sản phẩm không hợp lệ.");
            req.getRequestDispatcher("/product.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Lỗi khi tải sản phẩm.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (!isAdmin(req)) { resp.sendError(403); return; }

        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String priceText = req.getParameter("price");
        String quantityText = req.getParameter("soluong");
        String[] images = req.getParameterValues("images");

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                BigDecimal price = new BigDecimal(priceText);
                int quantity = Integer.parseInt(quantityText);
                int productId;

                if ("add".equals(action)) {
                    String sql = "INSERT INTO product(name, price, soluong) VALUES(?,?,?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                        ps.setString(1, name);
                        ps.setBigDecimal(2, price);
                        ps.setInt(3, quantity);
                        ps.executeUpdate();
                        try (ResultSet keys = ps.getGeneratedKeys()) { keys.next(); productId = keys.getInt(1); }
                    }
                } else if ("update".equals(action)) {
                    productId = Integer.parseInt(req.getParameter("id"));
                    String sql = "UPDATE product SET name=?, price=?, soluong=? WHERE id=?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setString(1, name); ps.setBigDecimal(2, price);
                        ps.setInt(3, quantity); ps.setInt(4, productId);
                        ps.executeUpdate();
                    }
                    try (PreparedStatement ps = conn.prepareStatement("DELETE FROM product_images WHERE product_id=?")) {
                        ps.setInt(1, productId); ps.executeUpdate();
                    }
                } else {
                    conn.rollback(); resp.sendRedirect(req.getContextPath()+"/product"); return;
                }

                if (images != null) {
                    String imageSql = "INSERT INTO product_images(product_id, image_url) VALUES(?,?)";
                    try (PreparedStatement ps = conn.prepareStatement(imageSql)) {
                        for (String image : images) {
                            if (image != null && !image.trim().isEmpty()) {
                                ps.setInt(1, productId); ps.setString(2, image.trim()); ps.addBatch();
                            }
                        }
                        ps.executeBatch();
                    }
                }
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
            resp.sendRedirect(req.getContextPath()+"/product");
        } catch (Exception e) {
            throw new ServletException("Không thể lưu sản phẩm. Kiểm tra dữ liệu nhập.", e);
        }
    }
}
