package com.ecommerce.core.order.rabbitmq;

import com.ecommerce.api.core.order.models.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    private RabbitTemplate rabbitTemplate;
    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Order order) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, order);
    }
}
