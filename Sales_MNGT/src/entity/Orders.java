package entity;
import java.time.LocalDateTime;

public class Orders {
    private int orderID;
    private int customerID;
    private LocalDateTime orderDate;
    private int total;
    private String status;

    public Orders() {
    }

    public Orders(int orderID, int customerID, LocalDateTime orderDate, int total, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.total = total;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderID=" + orderID +
                ", customerID=" + customerID +
                ", orderDate=" + orderDate +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }
}
