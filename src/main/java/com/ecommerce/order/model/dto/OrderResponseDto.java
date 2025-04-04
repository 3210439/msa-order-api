package com.ecommerce.order.model.dto;

import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.type.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
  private Long id;
  private Long userId;
  private Long productId;
  private Integer quantity;
  private OrderStatus status;

  @Builder
  public OrderResponseDto(Order order) {
    this.id = order.getId();
    this.userId = order.getUserId();
    this.productId = order.getProductId();
    this.quantity = order.getQuantity();
    this.status = order.getStatus();
  }
}