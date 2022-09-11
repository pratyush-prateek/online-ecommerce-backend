package com.ecommerce.core.order.services;

import com.ecommerce.api.core.order.OrderService;
import com.ecommerce.api.core.order.models.Order;
import com.ecommerce.core.order.rabbitmq.RabbitMQSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderServiceImpl implements OrderService {
    private RabbitMQSender rabbitMQSender;
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    public OrderServiceImpl(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @Override
    public ResponseEntity placeOrder(Order order) {
        LOG.info(order.getProductId());
        if(order == null)
            return ResponseEntity.badRequest().build();

        this.rabbitMQSender.send(order);
        return ResponseEntity.ok(order);
    }

    @Override
    public ResponseEntity cancelOrder() {
        return null;
    }
}
