package dao;
import entity.Product;
import utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    //4. Thêm sản phẩm vào giỏ hàng
    public void addToCart (int customerID, int productID, int so_luong) {
        List<Product> products = new ArrayList<>();

        //Câu lệnh SQL
        String findCartSQL = "SELECT cartID FROM Cart WHERE customerID = ?";
        String checkItemSQL = "SELECT so_luong FROM Cart_Item WHERE cartID = ? AND productID = ?";
        String updateItemSQL = "UPDATE Cart_Item SET so_luong = so_luong + ? WHERE cartID = ? AND productID = ?";
        String insertItemSQL = "INSERT INTO Cart_Item (cartID, productID, so_luong) VALUES (?, ?, ?)";

        //B1. Kiểm tra giỏ hàng thuộc khách hàng nào
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement findCartPS = conn.prepareStatement(findCartSQL);
            findCartPS.setInt(1, customerID);
            ResultSet cartRS = findCartPS.executeQuery();

            if (cartRS.next()) {
                int cartID = cartRS.getInt("cartID");

                //B2. Kiểm tra sản phẩm có trong giỏ hay chưa
                PreparedStatement checkItemPS  = conn.prepareStatement(checkItemSQL);
                checkItemPS.setInt(1, cartID);
                checkItemPS.setInt(2, productID);
                ResultSet itemRS = checkItemPS.executeQuery();

                //B2.1. Nếu có rồi thì cập nhật
                if (itemRS.next()) {
                    PreparedStatement updateItemPS = conn.prepareStatement(updateItemSQL);
                    updateItemPS.setInt(1, so_luong);
                    updateItemPS.setInt(2, cartID);
                    updateItemPS.setInt(3, productID);
                    updateItemPS.executeUpdate();
                    updateItemPS.close();
                    System.out.println("Đã cập nhật số lượng trong giỏ");
                } else  {
                    //B2.2. Nếu chưa có thì thêm vào
                    PreparedStatement insertItemPS = conn.prepareStatement(insertItemSQL);
                    insertItemPS.setInt(1, cartID);
                    insertItemPS.setInt(2, productID);
                    insertItemPS.setInt(3, so_luong);
                    insertItemPS.executeUpdate();
                    insertItemPS.close();
                    System.out.println("Đã thêm vào giỏ");
                }

                itemRS.close();
                checkItemPS.close();
            } else {
                System.out.println("Không tìm thấy sản phẩm");
            }

            cartRS.close();
            findCartPS.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
    }

    //5. Xóa sản phẩm khỏi giỏ hàng
    public void removeFromCart(int customerID, int productID) {
        String findCartSQL = "SELECT cartID FROM Cart WHERE customerID = ?";
        String deleteItemSQL = "DELETE FROM Cart_Item WHERE cartID = ? AND productID = ?";

        Connection conn = DBConnection.getConnection();
        try {
            //B1. Kiểm tra giỏ hàng thuộc khách hàng nào
            PreparedStatement findCartPS = conn.prepareStatement(findCartSQL);
            findCartPS.setInt(1, customerID);
            ResultSet cartRS = findCartPS.executeQuery();

            //Nếu có giỏ hàng
            if (cartRS.next()) {
                int cartID = cartRS.getInt("cartID");

                //B2. Xóa sản phẩm khỏi giỏ
                PreparedStatement deleteItemPS = conn.prepareStatement(deleteItemSQL);
                deleteItemPS.setInt(1, cartID);
                deleteItemPS.setInt(2, productID);
                int result = deleteItemPS.executeUpdate();
                if (result > 0) {
                    System.out.println("Đã xóa sản phẩm khỏi giỏ");
                } else {
                    System.out.println("Sản phẩm không có trong giỏ");
                }

                deleteItemPS.close();
            } else {
                //Nếu không có giỏ hàng thì gửi thông báo
                System.out.println("Không tìm thấy giỏ hàng");
            }

            cartRS.close();
            findCartPS.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
    }

    //6. Cập nhật số lượng trong giỏ hàng
    public void updateCartItem (int customerID, int productID, int so_luong) {
        String findCartSQL = "SELECT cartID FROM Cart WHERE customerID = ?";
        String updateItemSQL = "UPDATE Cart_Item SET so_luong = ? WHERE cartID = ? AND productID = ?";

        Connection conn = DBConnection.getConnection();
        try {
            //B1. Kiểm tra giỏ hàng thuộc khách hàng nào
            PreparedStatement findCartPS = conn.prepareStatement(findCartSQL);
            findCartPS.setInt(1, customerID);
            ResultSet cartRS = findCartPS.executeQuery();

            if (cartRS.next()) {
                int cartID = cartRS.getInt("cartID");

                //B2. Cập nhật số lượng
                PreparedStatement updateItemPS = conn.prepareStatement(updateItemSQL);
                updateItemPS.setInt(1, so_luong);
                updateItemPS.setInt(2, cartID);
                updateItemPS.setInt(3, productID);
                int result = updateItemPS.executeUpdate();
                if (result > 0) {
                    System.out.println("Đã cập nhật số lượng");
                } else {
                    System.out.println("Sản phẩm không có trong giỏ");
                }

                updateItemPS.close();
            } else {
                System.out.println("Không tìm thấy giỏ hàng");
            }

            cartRS.close();
            findCartPS.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
    }

    //7. Xem giỏ hàng
    public void getCartItem(int customerID) {
        String sql = "SELECT ci.productID, p.name, p.price, ci.so_luong FROM Cart_Item ci " +
                "JOIN Cart c ON ci.cartID = c.cartID " +
                "JOIN Product p ON ci.productID = p.productID " +
                "WHERE c.customerID = ?";

        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            System.out.println("----------------CART-----------------");

            boolean hasProduct = false;
            while (rs.next()) {
                hasProduct = true;
                System.out.println(
                        "ID: " + rs.getInt("productID") +
                                " | Tên: " + rs.getString("name") +
                                " | Giá: " + rs.getInt("price") +
                                " | Số lượng: " + rs.getInt("so_luong")
                );
            }

            if (!hasProduct) {
                System.out.println("Giỏ hàng đang trống!");
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
    }
}