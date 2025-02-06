package com.ninedocs.userserver.userprofile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.ninedocs.userserver.user.application.deleteuser.exception.NullUserException;
import com.ninedocs.userserver.user.application.userprofile.UserProfileQueryService;
import com.ninedocs.userserver.user.application.userprofile.dto.UserProfileResponse;
import com.ninedocs.userserver.user.persistence.User;
import com.ninedocs.userserver.user.persistence.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserProfileTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserProfileQueryService userProfileService;

  @Test
  void testUserCheck_UserExists() {
    // Arrange
    long userId = 1L;
    User mockUser = User.builder()
        .id(userId)
        .email("test@example.com")
        .nickname("testUser")
        .build();

    when(userRepository.findByIdAndDeletedAtIsNull(userId))
        .thenReturn(Optional.of(mockUser));

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
    when(userRepository.findByIdAndDeletedAtIsNull(userId)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(NullUserException.class, () -> userProfileService.getUserProfile(userId));
  }
}
