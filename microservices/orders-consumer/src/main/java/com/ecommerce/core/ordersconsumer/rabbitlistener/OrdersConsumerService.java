package com.ecommerce.core.ordersconsumer.rabbitlistener;

import com.ecommerce.api.core.order.OrderRepository;
import com.ecommerce.api.core.order.models.CancellationRequest;
import com.ecommerce.api.core.order.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersConsumerService {
    private static final Logger LOG = LoggerFactory.getLogger(OrdersConsumerService.class);

    @Autowired
    private OrderRepository orderRepository;
    public void ordersListener(Order order) {
        LOG.info("New order request received");
        this.orderRepository.save(order);
    }

    public void orderCancellationListener(CancellationRequest cancellationRequest) {
        LOG.info("Cancel request received: " + cancellationRequest.toString());
    }
}
