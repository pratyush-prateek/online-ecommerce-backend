package com.ecommerce.core.ordersconsumer.rabbitlistener;

import com.ecommerce.core.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersListenerConfig {
    @Bean
    SimpleMessageListenerContainer ordersListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter ordersListenerAdapter) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(RabbitMQConfig.ORDERS_QUEUE_NAME);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(3);
        simpleMessageListenerContainer.setMessageListener(ordersListenerAdapter);
        return simpleMessageListenerContainer;
    }

    @Bean
    MessageListenerAdapter ordersListenerAdapter(OrdersConsumerService ordersListener) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(ordersListener, "ordersListener");
        messageListenerAdapter.setMessageConverter(new Jackson2JsonMessageConverter());
        return messageListenerAdapter;
    }
}
