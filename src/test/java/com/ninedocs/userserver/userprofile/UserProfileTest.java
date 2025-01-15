package com.ninedocs.userserver.userprofile;

import com.ninedocs.userserver.user.application.deleteuser.exception.NullUserException;
import com.ninedocs.userserver.user.application.userprofile.UserProfileService;
import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileResponse;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserProfileTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserProfileService userProfileService;

  @Test
  void testUserCheck_UserExists() {
    // Arrange
    long userId = 1L;
    User mockUser = new User();
    mockUser.setId(userId);
    mockUser.setEmail("test@example.com");
    mockUser.setNickname("testUser");

    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

    // Act
    UserProfileResponse response = userProfileService.getUserProfile(userId);

    // Assert
    assertNotNull(response);
    assertEquals("test@example.com", response.getEmail());
    assertEquals("testUser", response.getNickname());
  }

  @Test
  void testUserCheck_UserDoesNotExist() {
    // Arrange
    long userId = 1L;
    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(NullUserException.class, () -> userProfileService.getUserProfile(userId));
  }
}
