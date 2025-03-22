package com.ecommerce.order.controller;

import com.ecommerce.order.model.UserActivityLog;
import com.ecommerce.order.service.LogService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserActivityController {

  @Autowired
  private LogService logService;

  @GetMapping("/click")
  public String logClick(@RequestParam String userId, @RequestParam String itemId,
      HttpSession session) {
    UserActivityLog log = new UserActivityLog();
    log.setUserId(userId);
    log.setSessionId(session.getId()); // 세션은 별도 로직으로 생성 가능
    log.setItemId(itemId);
    log.setPageUrl("/products/" + itemId);
    logService.logClick(log);
    return "Click logged";
  }

  @GetMapping("/expose")
  public String logExpose(@RequestParam String userId, @RequestParam String itemId,
      @RequestParam int position, HttpSession session) {
    UserActivityLog log = new UserActivityLog();
    log.setUserId(userId);
    log.setSessionId(session.getId());
    log.setItemId(itemId);
    log.setPosition(position);
    logService.logExpose(log);
    return "Expose logged";
  }

  @PostMapping("/purchase")
  public String logPurchase(@RequestParam String userId, @RequestParam String orderId,
      @RequestParam String itemId, @RequestParam int amount, HttpSession session) {
    UserActivityLog log = new UserActivityLog();
    log.setUserId(userId);
    log.setSessionId(session.getId());
    log.setOrderId(orderId);
    log.setItemId(itemId);
    log.setAmount(amount);
    logService.logPurchase(log);
    return "Purchase logged";
  }

  @GetMapping("/access")
  public String logAccess(@RequestParam String userId, @RequestParam String ipAddress,
      @RequestParam String requestUrl, HttpSession session) {
    UserActivityLog log = new UserActivityLog();
    log.setUserId(userId);
    log.setSessionId(session.getId());
    log.setIpAddress(ipAddress);
    log.setRequestUrl(requestUrl);
    log.setHttpMethod("GET");
    logService.logAccess(log);
    return "Access logged";
  }
}