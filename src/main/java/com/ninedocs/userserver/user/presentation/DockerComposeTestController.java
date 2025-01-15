package com.ninedocs.userserver.user.presentation;

import com.ninedocs.userserver.user.application.dockercomposetest.TestService;
import com.ninedocs.userserver.user.application.dockercomposetest.dto.TestRequest;
import com.ninedocs.userserver.user.application.dockercomposetest.dto.TestResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "테스트")
@RestController
@RequiredArgsConstructor
public class DockerComposeTestController {

  private final TestService testService;

  @GetMapping("/api/v1/user/dockercompose/test")
  public List<TestResponse> testUser() {
    return testService.getAllEmails();
  }

  @PostMapping("/api/v1/user/dockercompose")
  public String createUser(@RequestBody TestRequest testRequest) {
    return testService.save(testRequest);
  }
}
