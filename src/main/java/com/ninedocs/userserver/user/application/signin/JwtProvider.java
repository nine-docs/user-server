package com.ninedocs.userserver.user.application.signin;

import com.ninedocs.userserver.user.application.signin.dto.JwtTokenResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtProvider {

  private final long accessTokenExpirationMs; // final로 선언
  private final Key secretKey; // 추가: Key 객체를 저장할 필드

  public JwtProvider(@Value("${jwt.secret}") String secret,
      @Value("${jwt.access-token-expiration-ms}") long accessTokenExpirationMs) {
    this.accessTokenExpirationMs = accessTokenExpirationMs;
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes()); // 생성자에서 초기화
  }

  public JwtTokenResult generateAccessToken(long id) {
    Date now = new Date();
    Date expiryDate = generateExpiryDate(now);

    String token = Jwts.builder()
        .setSubject(String.valueOf(id))
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(secretKey, SignatureAlgorithm.HS512) // secretKey 사용
        .compact();

    return JwtTokenResult.builder()
        .expiredAt(convertToLocalDateTime(expiryDate))
        .token(token)
        .build();
  }

  private Date generateExpiryDate(Date now) {
    return new Date(now.getTime() + accessTokenExpirationMs);
  }

  private LocalDateTime convertToLocalDateTime(Date date) {
    return date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }
}
