package com.ecommerce.api.core.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductService {
    /*
    * Sample usage: curl $HOST:$PORT/products
    * @param none
    * @returns list of all products
    * */
    @GetMapping(value = "/products", produces="application/json")
    ResponseEntity<List<Product>> getAllProducts(@RequestParam(value="categoryId", required=false)String categoryId);

    /*
    * Sample usage: curl $HOST:$PORT/products/{productId}
    * @param productId
    * @returns Product with the id, else null
    * */

    @GetMapping(value = "/products/{productId}", produces="application/json")
    ResponseEntity<Product> getProduct(@PathVariable String productId);

    /*
    * Sample usage: curl $HOST:$PORT/products
    * @body - Product
    * @returns - Product with ID
    * */
    @PostMapping(value = "/products", produces="application/json")
    ResponseEntity<Product> createProduct(@RequestBody Product product);

    /*
    * Delete a product
    * */
    @DeleteMapping(value = "/products/{productId}")
    ResponseEntity deleteProduct(@PathVariable String productId);
}
