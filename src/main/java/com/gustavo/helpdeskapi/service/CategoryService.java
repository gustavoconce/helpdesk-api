package com.gustavo.helpdeskapi.service;

import com.gustavo.helpdeskapi.dto.CategoryCreateDTO;
import com.gustavo.helpdeskapi.dto.CategoryDTO;
import com.gustavo.helpdeskapi.entity.Category;
import com.gustavo.helpdeskapi.entity.User;
import com.gustavo.helpdeskapi.mapper.CategoryMapper;
import com.gustavo.helpdeskapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO createCategory(CategoryCreateDTO dto) {

        Category category = CategoryMapper.toEntity(dto);

        Category savedCategory = categoryRepository.save(category);

        return CategoryMapper.toDTO(savedCategory);
    }

    public List<CategoryDTO> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(CategoryMapper::toDTO).toList();

    }
}
