package com.gustavo.helpdeskapi.repository;

import com.gustavo.helpdeskapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
