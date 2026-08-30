package dao;

import entity.Product;
import utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    //1. Xem danh sách sản phẩm
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";

        Connection conn = DBConnection.getConnection();
        try (

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

        } finally {
            DBConnection.closeConnection(conn);
        }
        return products;
    }

    //2. Xem chi tiết sản phẩm
    public Product getProductById(int productID) {
        Product product = null;
        String sql = "SELECT * FROM Product WHERE productID = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                product = new Product();

                product.setProductID(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    //3. Tìm kiếm sản phẩm
    public List<Product> searchProducts (String productName) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE name LIKE ?";

        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + productName + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Product product = new Product();

                product.setProductID(rs.getInt("productID"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            DBConnection.closeConnection(conn);
        }
        return products;
    }
}