package com.ecommerce.order.service;

import com.ecommerce.order.model.dto.AccessLogRequest;
import com.ecommerce.order.model.dto.ClickLogRequest;
import com.ecommerce.order.model.dto.ExposeLogRequest;
import com.ecommerce.order.model.dto.PurchaseLogRequest;
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

  public void logClick(ClickLogRequest clickLogRequest){
    CLICK_LOGGER.info(clickLogRequest.toLogString());
  }

  public void logExpose(ExposeLogRequest exposeLogRequest) {
    EXPOSE_LOGGER.info(exposeLogRequest.toLogString());
  }

  public void logPurchase(PurchaseLogRequest purchaseLogRequest) {
    PURCHASE_LOGGER.info(purchaseLogRequest.toLogString());
  }

  public void logAccess(AccessLogRequest accessLogRequest) {
    ACCESS_LOGGER.info(accessLogRequest.toLogString());
  }
}