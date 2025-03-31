package com.ecommerce.order.controller;

import com.ecommerce.order.event.OrderEventPublisher;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderEventPublisher orderEventPublisher;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        order.setStatus("PENDING");
        orderService.createOrder(order);
        orderEventPublisher.publishOrderCreated(order);

        return ResponseEntity.ok("Order Created: " + order.getId().toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}