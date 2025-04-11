package com.ecommerce.order.controller;

import com.ecommerce.order.model.dto.AccessLogRequest;
import com.ecommerce.order.model.dto.ClickLogRequest;
import com.ecommerce.order.model.dto.ExposeLogRequest;
import com.ecommerce.order.model.dto.PurchaseLogRequest;
import com.ecommerce.order.service.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserActivityController {

  @Autowired
  private LogService logService;

  @GetMapping("/click")
  public String logClick(ClickLogRequest clickLogRequest, HttpSession session) {
    clickLogRequest.setSessionId(session.getId());
    logService.logClick(clickLogRequest);
    return "Click logged";
  }

  @GetMapping("/expose")
  public String logExpose(ExposeLogRequest exposeLogRequest, HttpSession session) {
    exposeLogRequest.setSessionId(session.getId());
    logService.logExpose(exposeLogRequest);
    return "Expose logged";
  }

  @PostMapping("/purchase")
  public String logPurchase(@RequestBody PurchaseLogRequest purchaseLogRequest, HttpSession session) {
    purchaseLogRequest.setSessionId(session.getId());
    logService.logPurchase(purchaseLogRequest);
    return "Purchase logged";
  }

  @GetMapping("/access")
  public String logAccess(AccessLogRequest accessLogRequest, HttpSession session) {
    accessLogRequest.setSessionId(session.getId());
    logService.logAccess(accessLogRequest);
    return "Access logged";
  }
}