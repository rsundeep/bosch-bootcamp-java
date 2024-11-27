package com.bosch.productapp.model;

import java.io.Serializable;

public class Product implements Serializable {
    public  static  final long SerialVersionID = 1L;


   private int id;
    private ProductCategory category;
    private String description;
    private double price;
    private boolean available;
    public Product()
    {}
    public Product(int id, ProductCategory category, String name, double price, boolean available) {
        this.id = id;
        this.category = category;
        this.description = name;
        this.price = price;
        this.available = available;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + description + '\'' +
                ", price=" + price +
                ", active=" + available +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
