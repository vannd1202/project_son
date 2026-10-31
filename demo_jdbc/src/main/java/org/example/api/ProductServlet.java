package org.example.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.ProductDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<ProductDTO> list = new ArrayList<>();
        //Kiểm tra thử kết nối DB
        Connection conn = DBConnection.getConnection();

        //Search
        String sql = "SELECT * FROM product WHERE 1=1";

        String search = request.getParameter("search");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String action = request.getParameter("action");
        String sort = request.getParameter("sort");

        if (search != null && !search.trim().isEmpty()) {
            sql += " AND name LIKE ?";
        }

        if (minPrice != null && !minPrice.trim().isEmpty()) {
            sql += " AND price >= ?";
        }

        if (maxPrice != null && !maxPrice.trim().isEmpty()) {
            sql += " AND price <= ?";
        }

        //Sort (theo giá)
        if ("asc".equals(sort)) {
            sql += " ORDER BY price ASC";
        } else if ("desc".equals(sort)) {
            sql += " ORDER BY price DESC";
        }

        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            int index = 1;

            //Tìm theo tên
            if (search != null && !search.trim().isEmpty()) {
                statement.setString(index++, "%" + search.trim() + "%");
            }

            //Tìm theo giá thấp nhất
            if (minPrice != null && !minPrice.trim().isEmpty()) {
                statement.setBigDecimal(index++, new java.math.BigDecimal(minPrice));
            }

            //Tìm theo giá cao nhất
            if (maxPrice != null && !maxPrice.trim().isEmpty()) {
                statement.setBigDecimal(index++, new java.math.BigDecimal(maxPrice));
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductDTO productDTO = new ProductDTO ();
                productDTO.setId(resultSet.getInt("id"));
                productDTO.setName(resultSet.getString("name"));
                productDTO.setImage(resultSet.getString("image_1"));
                productDTO.setPrice(resultSet.getString("price"));
                productDTO.setSoluong(resultSet.getString("soluong"));
                list.add(productDTO);
            }

            //Delete
            if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));

                String deleteSQL = "DELETE FROM product WHERE id = ?";
                PreparedStatement deleteStatement = conn.prepareStatement(deleteSQL);

                deleteStatement.setInt(1, id);
                deleteStatement.executeUpdate();

                deleteStatement.close();
                conn.close();

                response.sendRedirect(request.getContextPath() + "/product");
                return;
            }

            request.setAttribute("list_product", list);
            //Chuyển hướng sang trang index.jsp
            request.getRequestDispatcher("/product.jsp").forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        //Add
        if ("add".equals(action)) {
            String name = req.getParameter("name");
            String image = req.getParameter("image");
            String price = req.getParameter("price");
            String soluong = req.getParameter("soluong");

            String sql = "INSERT INTO product (name, image_1, price, soluong) VALUES (?, ?, ?, ?)";

            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setString(1, name);
                statement.setString(2, image);
                statement.setString(3, price);
                statement.setString(4, soluong);

                statement.executeUpdate();

                statement.close();
                conn.close();

                resp.sendRedirect(req.getContextPath() + "/product");
                return;

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        //Update
        if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String image = req.getParameter("image");
            String price = req.getParameter("price");
            String soluong = req.getParameter("soluong");

            String sql = "UPDATE product SET name = ?, image_1 = ?, price = ?, soluong = ? WHERE id = ?";

            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setString(1, name);
                statement.setString(2, image);
                statement.setString(3, price);
                statement.setString(4, soluong);
                statement.setInt(5, id);

                statement.executeUpdate();

                statement.close();
                conn.close();

                resp.sendRedirect(req.getContextPath() + "/product");

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
    }

}
