package org.example.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.dto.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    private Map<Integer, Integer> getCart(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null) { cart = new LinkedHashMap<>(); session.setAttribute("cart", cart); }
        return cart;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<Integer, Integer> cart = getCart(req);
        List<CartItemDTO> lines = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            for (Map.Entry<Integer,Integer> entry : cart.entrySet()) {
                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE id=?")) {
                    ps.setInt(1, entry.getKey());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            ProductDTO p = new ProductDTO();
                            p.setId(rs.getInt("id")); p.setName(rs.getString("name"));
                            p.setPrice(rs.getBigDecimal("price")); p.setSoluong(rs.getInt("soluong"));
                            try (PreparedStatement imagePs = conn.prepareStatement(
                                    "SELECT image_url FROM product_images WHERE product_id=? ORDER BY id LIMIT 1")) {
                                imagePs.setInt(1, p.getId());
                                try (ResultSet imageRs = imagePs.executeQuery()) {
                                    if (imageRs.next()) p.getImages().add(imageRs.getString(1));
                                }
                            }
                            lines.add(new CartItemDTO(p, entry.getValue()));
                        }
                    }
                }
            }
        } catch (SQLException e) { throw new ServletException(e); }
        req.setAttribute("cartLines", lines);
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<Integer, Integer> cart = getCart(req);
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));
        if ("add".equals(action)) {
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            if (quantity > 0) cart.put(id, cart.getOrDefault(id, 0) + quantity);
        } else if ("remove".equals(action)) cart.remove(id);
        resp.sendRedirect(req.getContextPath()+"/cart");
    }
}
