package com.ninedocs.userserver.user.presentation.usermanagement.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class UserBulkQueryRequest {

  private List<Long> userIds;
}
