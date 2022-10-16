package com.ecommerce.core.product.services;

import com.ecommerce.api.core.product.Product;
import com.ecommerce.api.core.product.ProductRepository;
import com.ecommerce.api.core.product.ProductService;
import com.ecommerce.util.exceptions.InvalidInputException;
import com.ecommerce.util.http.ServiceUtil;
import com.mongodb.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductServiceImpl implements ProductService {
    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;
    private final ServiceUtil serviceUtil;

    @Autowired
    public ProductServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts(String categoryId) {
        if(categoryId == null)
            return ResponseEntity.ok(productRepository.findAll());

        //Add a check for a valid category using category service
        List<Product> productsByCategoryId = productRepository.findByCategoryId(categoryId);
        return ResponseEntity.ok(productsByCategoryId);
    }

    @Override
    public ResponseEntity<Product> getProduct(String productId) {
        try {
            Optional<Product> product = productRepository.findById(productId);
            Product result = product.get();
            return ResponseEntity.ok(result);
        }
        catch (IllegalArgumentException e) {
            LOG.warn("Product with id cannot be null");
            throw e;
        }
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        try {
            return ResponseEntity.ok(productRepository.save(product));
        }
        catch(DuplicateKeyException e) {
            LOG.warn(e.getMessage());
            throw new InvalidInputException("Product with key " + product.getProductId() + " already exists");
        }
    }

    @Override
    public ResponseEntity deleteProduct(String productId) {
        try {
            productRepository.deleteById(productId);
            return ResponseEntity.noContent().build();
        }
        catch(IllegalArgumentException e) {
            LOG.warn("ProductID cannot be null");
            throw new InvalidInputException("ProductId cannot be null");
        }
    }
}
