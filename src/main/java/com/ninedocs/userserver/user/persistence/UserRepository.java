package com.ninedocs.userserver.user.persistence;


import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByIdAndDeletedAtIsNull(long id);

  @Query("select u from User u where u.id in :userIds and u.deletedAt is null")
  List<User> findUsers(@Param("userIds") List<Long> userIds);

  Optional<User> findByEmailAndDeletedAtIsNull(String email);

  Optional<User> findByEmail(String email);
}
