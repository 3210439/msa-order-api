package com.ecommerce.order.model.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import lombok.Data;

@Data
public class ClickLogRequest {
  private String timestamp;
  private String userId;
  private String sessionId; // 컨트롤러에서 설정
  private String itemId;
  private String pageUrl;

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public String toLogString() {
    timestamp = Instant.now().toString();
    try {
      return objectMapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
