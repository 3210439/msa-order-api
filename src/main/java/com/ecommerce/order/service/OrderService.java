package com.ecommerce.order.service;

import com.ecommerce.order.event.OrderEventPublisher;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.dto.OrderRequestDto;
import com.ecommerce.order.model.dto.OrderResponseDto;
import com.ecommerce.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    public OrderService(OrderRepository orderRepository, OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto request) {
        Order order = request.toEntity();
        Order savedOrder = orderRepository.save(order);
        orderEventPublisher.publishOrderCreated(savedOrder);

        return OrderResponseDto.builder().order(savedOrder).build();
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return OrderResponseDto.builder().order(order).build();
    }
}
