package com.ecommerce.order.service;

import com.ecommerce.order.model.UserActivityLog;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class LogService {

  private static final String LOG_DIR = "logs/"; // 로그 디렉토리

  public void logClick(UserActivityLog log) {
    writeToFile("user_click.log", log.toClickString());
  }

  public void logExpose(UserActivityLog log) {
    writeToFile("user_expose.log", log.toExposeString());
  }

  public void logPurchase(UserActivityLog log) {
    writeToFile("user_purchase.log", log.toPurchaseString());
  }

  public void logAccess(UserActivityLog log) {
    writeToFile("access.log", log.toAccessString());
  }

  private void writeToFile(String fileName, String content) {
    String filePath = Paths.get(LOG_DIR, fileName).toString();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
      writer.write(content);
      writer.newLine();
    } catch (IOException e) {
      e.printStackTrace(); // 실제 환경에서는 로깅 프레임워크 사용
    }
  }
}