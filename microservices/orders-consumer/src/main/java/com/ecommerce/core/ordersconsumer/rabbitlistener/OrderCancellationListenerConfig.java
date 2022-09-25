package com.ecommerce.core.ordersconsumer.rabbitlistener;

import com.ecommerce.core.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderCancellationListenerConfig {
    @Bean
    SimpleMessageListenerContainer ordersCancellationListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter ordersCancellationListenerAdapter) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(RabbitMQConfig.CANCELLATION_QUEUE_NAME);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(2);
        simpleMessageListenerContainer.setMessageListener(ordersCancellationListenerAdapter);
        return simpleMessageListenerContainer;
    }

    @Bean
    MessageListenerAdapter ordersCancellationListenerAdapter(OrdersConsumerService ordersListener) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(ordersListener, "orderCancellationListener");
        messageListenerAdapter.setMessageConverter(new Jackson2JsonMessageConverter());
        return messageListenerAdapter;
    }
}
