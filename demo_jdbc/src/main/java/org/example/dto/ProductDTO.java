package org.example.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    private int id;
    private String name;
    private BigDecimal price;
    private int soluong;
    private List<String> images = new ArrayList<>();

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getFirstImage() {
        return images.isEmpty() ? "" : images.get(0);
    }
}
