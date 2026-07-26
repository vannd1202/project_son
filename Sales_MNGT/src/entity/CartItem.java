package entity;

public class CartItem {
    private int cartItemID;
    private int cartID;
    private int productID;
    private int soluong;

    public CartItem() {
    }

    public CartItem(int cartItemID, int cartID, int productID, int so_luong) {
        this.cartItemID = cartItemID;
        this.cartID = cartID;
        this.productID = productID;
        this.soluong = so_luong;
    }

    public int getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(int cartItemID) {
        this.cartItemID = cartItemID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSo_luong() {
        return soluong;
    }

    public void setSo_luong(int so_luong) {
        this.soluong = so_luong;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemID=" + cartItemID +
                ", cartID=" + cartID +
                ", productID=" + productID +
                ", so_luong=" + soluong +
                '}';
    }
}
