package com.ecommerce.order.controller;

import com.ecommerce.order.model.dto.OrderRequestDto;
import com.ecommerce.order.model.dto.OrderResponseDto;
import com.ecommerce.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

  @Mock
  private OrderService orderService;

  @InjectMocks
  private OrderController orderController;

  @Test
  void shouldCreateOrderAndReturnSuccessMessage() {
    // Given
    OrderRequestDto request = mock(OrderRequestDto.class);
    OrderResponseDto responseDto = mock(OrderResponseDto.class);

    when(orderService.createOrder(request)).thenReturn(responseDto);
    when(responseDto.getId()).thenReturn(1L);

    // When
    ResponseEntity<String> response = orderController.createOrder(request);

    // Then
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Order Created: 1", response.getBody());
    verify(orderService).createOrder(request);
    verify(responseDto).getId();
    verifyNoMoreInteractions(orderService, responseDto);
  }

  @Test
  void shouldGetOrderByIdAndReturnResponse() {
    // Given
    Long orderId = 1L;
    OrderResponseDto responseDto = mock(OrderResponseDto.class);

    when(orderService.getOrderById(orderId)).thenReturn(responseDto);

    // When
    ResponseEntity<OrderResponseDto> response = orderController.getOrderById(orderId);

    // Then
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(responseDto, response.getBody());
    verify(orderService).getOrderById(orderId);
    verifyNoMoreInteractions(orderService);
  }

  @Test
  void shouldHandleOrderNotFoundException() {
    // Given
    Long orderId = 999L;
    when(orderService.getOrderById(orderId))
        .thenThrow(new IllegalArgumentException("Order not found"));

    // When & Then
    try {
      orderController.getOrderById(orderId);
      fail("Expected IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Order not found", e.getMessage());
    }
    verify(orderService).getOrderById(orderId);
    verifyNoMoreInteractions(orderService);
  }
}