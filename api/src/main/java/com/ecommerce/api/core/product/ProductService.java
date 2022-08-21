package com.ecommerce.api.core.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {
    /*
    * Sample usage: curl $HOST:$PORT/products
    * @param none
    * @returns list of all products
    * */
    @GetMapping(value = "/products", produces="application/json")
    List<Product> getAllProducts();

    /*
    * Sample usage: curl $HOST:$PORT/products/{productId}
    * @param productId
    * @returns Product with the id, else null
    * */

    @GetMapping(value = "/products/{productId}", produces="application/json")
    Product getProduct(@PathVariable String productId);
}
