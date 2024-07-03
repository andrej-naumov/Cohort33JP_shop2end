package de.ait_tr.g_33_shop.domain.dto;

import java.util.Objects;

public class ProductSupplyDto {

    private Long id;
    private String title;
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSupplyDto that = (ProductSupplyDto) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, quantity);
    }

    @Override
    public String toString() {
        return String.format("Product: id - %d, title - %s, quantity - %d",
                id, title, quantity);
    }
}