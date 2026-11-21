package org.example.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String sql = "SELECT id, username, role FROM accounts WHERE username=? AND password=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HttpSession session = req.getSession();
                    session.setAttribute("accountId", rs.getInt("id"));
                    session.setAttribute("username", rs.getString("username"));
                    session.setAttribute("role", rs.getString("role"));
                    resp.sendRedirect(req.getContextPath() + "/product");
                } else {
                    req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Không thể đăng nhập. Hãy kiểm tra database.", e);
        }
    }
}
