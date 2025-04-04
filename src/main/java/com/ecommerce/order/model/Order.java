package com.ecommerce.order.model;

import com.ecommerce.order.model.common.BaseEntity;
import com.ecommerce.order.model.dto.OrderRequestDto;
import com.ecommerce.order.model.type.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Builder
    public Order(Long userId, Long productId, Integer quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = OrderStatus.PENDING;
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }
}