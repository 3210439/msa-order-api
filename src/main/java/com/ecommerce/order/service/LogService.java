package com.ecommerce.order.service;

import com.ecommerce.order.model.UserActivityLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {

  // 각 로그 유형별로 별도의 Logger 인스턴스 생성
  private static final Logger CLICK_LOGGER = LoggerFactory.getLogger("UserClickLogger");
  private static final Logger EXPOSE_LOGGER = LoggerFactory.getLogger("UserExposeLogger");
  private static final Logger PURCHASE_LOGGER = LoggerFactory.getLogger("UserPurchaseLogger");
  private static final Logger ACCESS_LOGGER = LoggerFactory.getLogger("AccessLogger");

  public void logClick(UserActivityLog log) {
    CLICK_LOGGER.info(log.toClickString());
  }

  public void logExpose(UserActivityLog log) {
    EXPOSE_LOGGER.info(log.toExposeString());
  }

  public void logPurchase(UserActivityLog log) {
    PURCHASE_LOGGER.info(log.toPurchaseString());
  }

  public void logAccess(UserActivityLog log) {
    ACCESS_LOGGER.info(log.toAccessString());
  }
}