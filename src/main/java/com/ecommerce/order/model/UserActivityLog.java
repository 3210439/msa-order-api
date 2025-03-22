package com.ecommerce.order.model;

import lombok.Data;

import java.time.Instant;

@Data
public class UserActivityLog {
  private String timestamp = Instant.now().toString();
  private String userId;
  private String sessionId;

  // Click Event
  private String itemId;
  private String pageUrl;

  // Expose Event
  private Integer position;

  // Purchase Event
  private String orderId;
  private Integer amount;

  // Access Log
  private String ipAddress;
  private String requestUrl;
  private String httpMethod;

  public String toClickString() {
    return String.join("|", timestamp, userId, sessionId, itemId, pageUrl);
  }

  public String toExposeString() {
    return String.join("|", timestamp, userId, sessionId, itemId, position.toString());
  }

  public String toPurchaseString() {
    return String.join("|", timestamp, userId, sessionId, orderId, itemId, amount.toString());
  }

  public String toAccessString() {
    return String.join("|", timestamp, userId, sessionId, ipAddress, requestUrl, httpMethod);
  }
}