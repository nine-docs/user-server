package com.ninedocs.userserver.user.application.emailverificationcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailSenderRequest {

  private String address;
  private String verificationCode;

}
