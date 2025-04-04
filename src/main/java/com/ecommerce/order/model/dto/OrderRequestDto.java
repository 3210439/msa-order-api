package com.ecommerce.order.model.dto;

import com.ecommerce.order.model.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequestDto {
  private Long userId;
  private Long productId;
  private Integer quantity;

  public Order toEntity() {
    return Order.builder()
        .userId(this.userId)
        .productId(this.productId)
        .quantity(this.quantity)
        .build();
  }
}