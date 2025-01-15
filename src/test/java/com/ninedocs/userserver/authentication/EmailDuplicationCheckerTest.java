package com.ninedocs.userserver.authentication;

import static org.mockito.Mockito.when;

import com.ninedocs.userserver.user.application.emailverificationcode.EmailDuplicationChecker;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmailDuplicationCheckerTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private EmailDuplicationChecker emailDuplicationChecker;

  private User user;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setEmail("example@example.com");
    user.setNickname("example");
    user.setPassword("example");
  }

  @Test
  public void checkEmailDuplication() {
    String email = "example@example.com";
    when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

    boolean isDuplicate = emailDuplicationChecker.isDuplicateEmail(email);

    Assertions.assertTrue(isDuplicate);
  }

}