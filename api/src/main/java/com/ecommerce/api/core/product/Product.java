package com.ecommerce.api.core.product;

import com.ecommerce.api.core.category.Category;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
public class Product {

    @Id
    private String productId;
    private String productName;
    private double cost;
    private String categoryId;

    public Product(String productId, String productName, double cost, String categoryId) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.cost = cost;
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getCost() {
        return cost;
    }

    public String getCategoryId() {
        return categoryId;
    }
}
