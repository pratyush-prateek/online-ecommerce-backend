package com.ecommerce.api.core.product;

import com.ecommerce.api.core.category.Category;

public class Product {
    private final int productId;
    private final String productName;
    private final double cost;
    private final Category category;

    public Product() {
        this.productId = 0;
        this.productName = null;
        this.cost = 0.0;
        this.category = null;
    }

    public Product(int productId, String productName, double cost, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.cost = cost;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getCost() {
        return cost;
    }

    public Category getCategory() {
        return category;
    }
}
