package com.ecommerce.order.model;

import com.ecommerce.order.model.common.BaseEntity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private String status;  // PENDING, COMPLETED, CANCELLED
}
