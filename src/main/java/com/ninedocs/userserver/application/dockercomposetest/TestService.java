package com.ninedocs.userserver.application.dockercomposetest;

import com.ninedocs.userserver.application.dockercomposetest.dto.TestRequest;
import com.ninedocs.userserver.application.dockercomposetest.dto.TestResponse;
import com.ninedocs.userserver.persistence.User;
import com.ninedocs.userserver.persistence.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

  private final UserRepository userRepository;

  public String save(TestRequest testRequest) {
    User user = User.builder().email(testRequest.getEmail()).password(testRequest.getPassword())
        .build();
    userRepository.save(user);
    return "Success";
  }

  public List<TestResponse> getAllEmails() {
    // findAll로 모든 User 엔티티 조회
    List<User> users = userRepository.findAll();

    // email 값만 추출하여 TestResponse DTO로 변환
    return users.stream()
        .map(user -> new TestResponse(user.getEmail()))
        .collect(Collectors.toList());
  }
}
