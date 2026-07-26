package dao;

import entity.Product;
import utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    //1. Xem danh sách sản phẩm
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ) {
            while(rs.next()) {
                Product product = new Product();

                product.setProductID(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));

                products.add(product);
        }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    //2. Xem chi tiết sản phẩm

}