package org.example.dto;

public class ProductDTO {
    private int id;
    private String name;
    private String image;
    private String price;
    private String soluong;

    public ProductDTO(int id, String name, String image, String price, String soluong) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.soluong = soluong;
    }

    public ProductDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
