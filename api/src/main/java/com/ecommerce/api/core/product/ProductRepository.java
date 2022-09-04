package com.ecommerce.api.core.product;

import  org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findByCategoryId(String categoryId);
}
