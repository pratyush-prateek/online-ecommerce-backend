package com.ecommerce.core.product.services;

import com.ecommerce.api.core.category.Category;
import com.ecommerce.api.core.product.Product;
import com.ecommerce.api.core.product.ProductService;
import com.ecommerce.util.http.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductServiceImpl implements ProductService {
    private final ServiceUtil serviceUtil;

    @Autowired
    public ProductServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    @Override
    public Product getProduct(int productId) {
        return new Product(2, "Cello", 4.5, new Category(45, "Pens"));
    }
}
