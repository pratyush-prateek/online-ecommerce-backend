package com.ecommerce.api.core.order;

import com.ecommerce.api.core.order.models.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderService {
    /*
    * Place an order
    * */
    @PostMapping(value = "/orders", produces="application/json")
    ResponseEntity placeOrder(@RequestBody Order order);

    /*
    * Cancel order
    * */
    @PostMapping(value = "/orders/{orderId}", produces="application/json")
    ResponseEntity cancelOrder();
}
