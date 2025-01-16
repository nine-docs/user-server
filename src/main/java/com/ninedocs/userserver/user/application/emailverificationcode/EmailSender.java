package com.ninedocs.userserver.user.application.emailverificationcode;

import com.ninedocs.userserver.common.presentation.dto.ApiResponse;
import com.ninedocs.userserver.user.application.emailverificationcode.dto.EmailSenderRequest;
import com.ninedocs.userserver.user.application.emailverificationcode.exception.EmailFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmailSender {

  private final WebClient webClient;

  public HttpStatusCode sendEmail(EmailSenderRequest request) {
    ResponseEntity<Void> response = webClient.post()
        .uri("/api/v1/mail/verification")
        .header("Content-Type", "application/json")
        .bodyValue(request)
        .retrieve()
        .toBodilessEntity()
        .block();
    return response.getStatusCode();
  }
}
