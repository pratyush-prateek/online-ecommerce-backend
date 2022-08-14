package com.ecommerce.api.core.category;

public class Category {
    private final int categoryId;
    private final String categoryName;

    public Category() {
        this.categoryId = 0;
        this.categoryName = null;
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
