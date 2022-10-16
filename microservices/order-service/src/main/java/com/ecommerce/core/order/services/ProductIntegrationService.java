package com.ecommerce.core.order.services;

import com.ecommerce.api.core.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ProductIntegrationService {
    private RestTemplate restTemplate;
    private String requestUrlTemplate;
    @Autowired
    public ProductIntegrationService(RestTemplate restTemplate, @Value("${application.name.product-service}") String productServiceName) {
        this.restTemplate = restTemplate;
        StringBuilder requestUrlBuilder = new StringBuilder();
        requestUrlBuilder.append("http://");
        requestUrlBuilder.append(productServiceName);
        requestUrlBuilder.append("/products/{productId}");
        this.requestUrlTemplate = requestUrlBuilder.toString();
    }

    public boolean checkProductExistWithId(String productId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(this.requestUrlTemplate);
        URI uri = uriBuilder.buildAndExpand(productId).toUri();
        try {
            Product product = this.restTemplate.getForObject(uri, Product.class);
            return product != null;
        }
        catch(RestClientException ex) {
            throw ex;
        }
    }
}
