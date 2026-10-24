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
        String sql = "select * from product";

        //Search
        String search = request.getParameter("search");
        String action = request.getParameter("action");
        if (search != null && !search.trim().isEmpty()) {
            sql = "select * from product where name like ?";
        }

        try {
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

            PreparedStatement statement = conn.prepareStatement(sql);

            if (search != null && !search.trim().isEmpty()) {
                statement.setString(1, "%" + search.trim() + "%");
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
