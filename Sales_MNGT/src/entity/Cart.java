package entity;

public class Cart {
    private int cartID;
    private int customerID;

    public Cart() {
    }

    public Cart(int cartID, int customerID) {
        this.cartID = cartID;
        this.customerID = customerID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                ", customerID=" + customerID +
                '}';
    }
}
