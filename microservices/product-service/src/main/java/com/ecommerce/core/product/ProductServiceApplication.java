package com.ecommerce.core.product;

import com.ecommerce.api.core.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("com.ecommerce")
@EnableMongoRepositories(basePackageClasses = ProductRepository.class)
@EnableEurekaClient
public class ProductServiceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceApplication.class);
	public static void main(String[] args) {
		//VM arguements for starting product service - -Djdk.tls.client.protocols=TLSv1.2
		ConfigurableApplicationContext context = SpringApplication.run(ProductServiceApplication.class, args);
		String mongoDBHost = context.getEnvironment().getProperty("spring.data.mongodb.host");
		String mongoDBPort = context.getEnvironment().getProperty("spring.data.mongodb.port");
		LOG.info("Connected to MongoDB products" + mongoDBHost + ":" + mongoDBPort);
	}
}
