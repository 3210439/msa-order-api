package com.ecommerce.order.controller;

import com.ecommerce.order.event.OrderEventPublisher;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.dto.OrderRequestDto;
import com.ecommerce.order.model.dto.OrderResponseDto;
import com.ecommerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto request) {
        OrderResponseDto response = orderService.createOrder(request);
        return ResponseEntity.ok("Order Created: " + response.getId().toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}