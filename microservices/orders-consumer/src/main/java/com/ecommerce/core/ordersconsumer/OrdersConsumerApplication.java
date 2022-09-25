package com.ecommerce.core.ordersconsumer;

import com.ecommerce.api.core.order.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {
        RabbitAutoConfiguration.class,
})
@EnableMongoRepositories(basePackageClasses = OrderRepository.class)
@ComponentScan("com.ecommerce")
public class OrdersConsumerApplication {
    private static Logger LOG = LoggerFactory.getLogger(OrdersConsumerApplication.class);
    public static void main(String[] args) {
        //VM arguements for starting orders consumer service - -Djdk.tls.client.protocols=TLSv1.2
        ConfigurableApplicationContext context = SpringApplication.run(OrdersConsumerApplication.class, args);
        String mongoDBHost = context.getEnvironment().getProperty("spring.data.mongodb.host");
        String mongoDBPort = context.getEnvironment().getProperty("spring.data.mongodb.port");
        LOG.info("Connected to MongoDB orders" + mongoDBHost + ":" + mongoDBPort);
    }
}
