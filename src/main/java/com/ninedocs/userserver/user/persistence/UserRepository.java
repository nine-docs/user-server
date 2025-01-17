package com.ninedocs.userserver.user.persistence;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByIdAndDeletedAtIsNull(long id);

  Optional<User> findByEmailAndDeletedAtIsNull(String email);

  Optional<User> findByEmail(String email);
}
