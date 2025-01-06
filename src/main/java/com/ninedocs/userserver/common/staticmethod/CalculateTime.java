package com.ninedocs.userserver.common.staticmethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalculateTime {

  public static String MakeExpiredAt(long startTime, long addMinute) {
    LocalDateTime startDateTime = LocalDateTime.ofEpochSecond(startTime / 1000, 0,
        java.time.ZoneOffset.UTC);
    LocalDateTime expiryDateTime = startDateTime.plusMinutes(addMinute);
    return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
        .format(expiryDateTime);
  }
}
