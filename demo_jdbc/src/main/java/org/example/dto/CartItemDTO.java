package org.example.dto;

import java.math.BigDecimal;

public class CartItemDTO {
    private ProductDTO product;
    private int quantity;

    public CartItemDTO(ProductDTO product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSubtotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
