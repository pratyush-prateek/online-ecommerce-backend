package com.ecommerce.core.product;

import com.ecommerce.api.core.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("com.ecommerce")
@EnableMongoRepositories(basePackageClasses = ProductRepository.class)
public class ProductServiceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceApplication.class);
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ProductServiceApplication.class, args);
		String mongoDBHost = context.getEnvironment().getProperty("spring.data.mongodb.host");
		String mongoDBPort = context.getEnvironment().getProperty("spring.data.mongodb.port");
		LOG.info("Connected to MongoDB " + mongoDBHost + ":" + mongoDBPort);
	}

}
