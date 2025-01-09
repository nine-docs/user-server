package com.ninedocs.userserver.user.application.signin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

  private final long accessTokenExpirationMs; // final로 선언
  private final Key secretKey; // 추가: Key 객체를 저장할 필드

  public JwtProvider(@Value("${jwt.secret}") String secret,
      @Value("${jwt.access-token-expiration-ms}") long accessTokenExpirationMs) {
    this.accessTokenExpirationMs = accessTokenExpirationMs;
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes()); // 생성자에서 초기화
  }

  public Date generateNow() {
    return new Date();
  }

  public Date generateExpiryDate(Date now) {
    return new Date(now.getTime() + accessTokenExpirationMs);
  }

  public String generateAccessToken(long id, Date expiryDate, Date now) {
    return Jwts.builder()
        .setSubject(String.valueOf(id))
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(secretKey, SignatureAlgorithm.HS512) // secretKey 사용
        .compact();
  }

  public LocalDateTime convertToLocalDateTime(Date date) {
    return date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }
}