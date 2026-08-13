package com.gustavo.helpdeskapi.repository;

import com.gustavo.helpdeskapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    Long id(Long id);
}
