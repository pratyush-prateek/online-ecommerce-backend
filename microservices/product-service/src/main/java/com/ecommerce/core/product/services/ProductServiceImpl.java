package com.ecommerce.core.product.services;

import com.ecommerce.api.core.category.Category;
import com.ecommerce.api.core.product.Product;
import com.ecommerce.api.core.product.ProductRepository;
import com.ecommerce.api.core.product.ProductService;
import com.ecommerce.util.http.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    private final ServiceUtil serviceUtil;

    @Autowired
    public ProductServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.get();
    }
}
