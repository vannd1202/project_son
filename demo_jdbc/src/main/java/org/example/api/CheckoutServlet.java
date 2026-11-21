package org.example.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath()+"/cart");
            return;
        }

        int accountId = (Integer) session.getAttribute("accountId");
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                BigDecimal total = BigDecimal.ZERO;
                for (Map.Entry<Integer,Integer> item : cart.entrySet()) {
                    try (PreparedStatement ps = conn.prepareStatement(
                            "SELECT price, soluong FROM product WHERE id=? FOR UPDATE")) {
                        ps.setInt(1, item.getKey());
                        try (ResultSet rs = ps.executeQuery()) {
                            if (!rs.next() || rs.getInt("soluong") < item.getValue())
                                throw new SQLException("Sản phẩm không đủ số lượng trong kho.");
                            total = total.add(rs.getBigDecimal("price")
                                    .multiply(BigDecimal.valueOf(item.getValue())));
                        }
                    }
                }

                int orderId;
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO orders(account_id, total, status) VALUES(?,?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, accountId); ps.setBigDecimal(2, total); ps.setString(3, "Đã đặt hàng");
                    ps.executeUpdate();
                    try (ResultSet keys = ps.getGeneratedKeys()) { keys.next(); orderId = keys.getInt(1); }
                }

                for (Map.Entry<Integer,Integer> item : cart.entrySet()) {
                    BigDecimal price;
                    try (PreparedStatement ps = conn.prepareStatement("SELECT price FROM product WHERE id=?")) {
                        ps.setInt(1, item.getKey());
                        try (ResultSet rs = ps.executeQuery()) { rs.next(); price = rs.getBigDecimal(1); }
                    }
                    try (PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO order_items(order_id, product_id, quantity, unit_price) VALUES(?,?,?,?)")) {
                        ps.setInt(1, orderId); ps.setInt(2, item.getKey());
                        ps.setInt(3, item.getValue()); ps.setBigDecimal(4, price); ps.executeUpdate();
                    }
                    try (PreparedStatement ps = conn.prepareStatement(
                            "UPDATE product SET soluong=soluong-? WHERE id=?")) {
                        ps.setInt(1, item.getValue()); ps.setInt(2, item.getKey()); ps.executeUpdate();
                    }
                }
                conn.commit();
                cart.clear();
                session.setAttribute("checkoutMessage", "Đặt hàng thành công! Mã đơn hàng: " + orderId);
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (Exception e) {
            throw new ServletException("Thanh toán không thành công: " + e.getMessage(), e);
        }
        resp.sendRedirect(req.getContextPath()+"/cart");
    }
}
