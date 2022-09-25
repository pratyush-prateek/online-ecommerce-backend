package com.ecommerce.core.order.rabbitsender;

import com.ecommerce.api.core.order.models.CancellationRequest;
import com.ecommerce.api.core.order.models.Order;
import com.ecommerce.core.rabbitmq.RabbitMQConfig;
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

    public void enqueueOrder(Order order) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDERS_TOPIC_EXCHANGE_NAME, RabbitMQConfig.ORDERS_ROUTING_KEY, order);
    }

    public void enqueueCancellationRequest(CancellationRequest cancellationRequest) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDERS_TOPIC_EXCHANGE_NAME, RabbitMQConfig.CANCELLATION_REQ_ROUTING_KEY, cancellationRequest);
    }
}
