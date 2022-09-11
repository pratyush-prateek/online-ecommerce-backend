package com.ecommerce.core.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class,
		RabbitAutoConfiguration.class,
})
@ComponentScan("com.ecommerce")
public class OrderServiceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(OrderServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
