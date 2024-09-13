package com.mtvs.backend.user.repository;

import com.mtvs.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
    User findById(long id);
}
