package org.example.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "ApiDemoServlet ", value = "/home")
public class ApiDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Kiểm tra thử kết nối DB
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            request.setAttribute("status", "Tôi đã kiểm database");
            request.setAttribute("name", "Nguyễn Văn A");
            request.setAttribute("age", "18 tuổi");
        } else {
            request.setAttribute("status", "Kết nối Database THẤT BẠI!");
        }

        // Chuyển hướng sang trang index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String name = req.getParameter("name");
        String age = req.getParameter("age");
        System.out.println("đã vào hàm cập nhật thông tin");
    }
}
