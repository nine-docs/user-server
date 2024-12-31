package com.ninedocs.userserver.presentation;

import com.ninedocs.userserver.application.dockercomposetest.TestService;
import com.ninedocs.userserver.application.dockercomposetest.dto.TestRequest;
import com.ninedocs.userserver.application.dockercomposetest.dto.TestResponse;
import com.ninedocs.userserver.persistence.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DockerComposeTestController {

  private final TestService testService;

  @GetMapping("/api/v1/user/test")
  public List<TestResponse> testUser() {
    return testService.getAllEmails();
  }

  @PostMapping("/api/v1/user")
  public String createUser(@RequestBody TestRequest testRequest) {
    return testService.save(testRequest);
  }
}
