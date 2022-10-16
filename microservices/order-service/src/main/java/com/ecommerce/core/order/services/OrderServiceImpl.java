package com.ecommerce.core.order.services;

import com.ecommerce.api.core.order.OrderService;
import com.ecommerce.api.core.order.models.CancellationRequest;
import com.ecommerce.api.core.order.models.Order;
import com.ecommerce.core.order.rabbitsender.RabbitMQSender;
import com.netflix.discovery.DiscoveryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderServiceImpl implements OrderService {
    private RabbitMQSender rabbitMQSender;
    private ProductIntegrationService productIntegrationService;
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    public OrderServiceImpl(RabbitMQSender rabbitMQSender, ProductIntegrationService productIntegrationService) {
        this.rabbitMQSender = rabbitMQSender;
        this.productIntegrationService = productIntegrationService;
    }

    @Override
    public ResponseEntity placeOrder(Order order) {
        if(order == null)
            return ResponseEntity.badRequest().build();

        String productId = order.getProductId();

        if(!this.productIntegrationService.checkProductExistWithId(productId))
            return ResponseEntity.notFound().build();

        this.rabbitMQSender.enqueueOrder(order);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity cancelOrder(CancellationRequest cancellationRequest) {
        if(cancellationRequest == null || cancellationRequest.getOrderId() == null)
            return ResponseEntity.badRequest().build();

        this.rabbitMQSender.enqueueCancellationRequest(cancellationRequest);
        return ResponseEntity.ok().build();
    }
}
