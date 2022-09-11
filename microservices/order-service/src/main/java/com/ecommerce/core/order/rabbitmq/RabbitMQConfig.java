package com.ecommerce.core.order.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    static final String ORDERS_TOPIC_EXCHANGE_NAME = "orders-topic";
    static final String ORDERS_QUEUE_NAME = "orders-pipeline";
    static final String ORDERS_ROUTING_KEY = "orders-routing-key";
    static final String CANCELLATION_QUEUE_NAME = "cancellation-pipeline";
    static final String CANCELLATION_REQ_ROUTING_KEY = "cancellation-req-routing-key";

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.virtual-host}")
    private String vHost;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public Queue ordersQueue() {
        return new Queue(ORDERS_QUEUE_NAME, true);
    }

    @Bean
    public Queue cancellationRequestQueue() {return new Queue(CANCELLATION_QUEUE_NAME, true); }

    @Bean
    public TopicExchange ordersTopicExchange() {
        return new TopicExchange(ORDERS_TOPIC_EXCHANGE_NAME);
    }

    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vHost);
        connectionFactory.setRequestedHeartBeat(30);
        connectionFactory.setConnectionTimeout(30000);
        return connectionFactory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Binding ordersBinding(TopicExchange topicExchange) {
        return BindingBuilder
                .bind(ordersQueue())
                .to(topicExchange)
                .with(ORDERS_ROUTING_KEY);
    }

    @Bean
    public Binding cancellationRequestBinding(TopicExchange topicExchange) {
        return BindingBuilder
                .bind(cancellationRequestQueue())
                .to(topicExchange)
                .with(CANCELLATION_REQ_ROUTING_KEY);
    }

    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
}
