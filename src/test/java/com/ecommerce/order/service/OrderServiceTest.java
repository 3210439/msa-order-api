package com.ecommerce.order.service;

import com.ecommerce.order.event.OrderEventPublisher;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.dto.OrderRequestDto;
import com.ecommerce.order.model.dto.OrderResponseDto;
import com.ecommerce.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private OrderEventPublisher orderEventPublisher;

  @InjectMocks
  private OrderService orderService;

  @Test
  void shouldCreateOrderSuccessfully() {
    // Given
    OrderRequestDto request = mock(OrderRequestDto.class);
    Order order = Order.builder().build();
    Order savedOrder = Order.builder().build();

    // Simulate the repository assigning an ID
    when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
      Order arg = invocation.getArgument(0);
      // Use reflection or a setter if Order has an ID field; here we assume it’s set post-save
      return savedOrder;
    });

    when(request.toEntity()).thenReturn(order);
    when(orderRepository.save(order)).thenReturn(savedOrder);

    // When
    OrderResponseDto response = orderService.createOrder(request);

    // Then
    assertNotNull(response);
    verify(orderRepository).save(order);
    verify(orderEventPublisher).publishOrderCreated(savedOrder);
    verifyNoMoreInteractions(orderRepository, orderEventPublisher);
  }

  @Test
  void shouldGetOrderByIdSuccessfully() {
    // Given
    Long orderId = 1L;
    Order order = Order.builder().build();
    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    // When
    OrderResponseDto response = orderService.getOrderById(orderId);

    // Then
    assertNotNull(response);
    verify(orderRepository).findById(orderId);
    verifyNoMoreInteractions(orderRepository);
  }

  @Test
  void shouldThrowExceptionWhenOrderNotFound() {
    // Given
    Long orderId = 999L;
    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    // When & Then
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> orderService.getOrderById(orderId));

    assertEquals("Order not found", exception.getMessage());
    verify(orderRepository).findById(orderId);
    verifyNoMoreInteractions(orderRepository);
  }
}