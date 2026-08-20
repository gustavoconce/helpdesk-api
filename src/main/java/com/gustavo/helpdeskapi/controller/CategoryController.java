package com.gustavo.helpdeskapi.controller;

import com.gustavo.helpdeskapi.dto.CategoryCreateDTO;
import com.gustavo.helpdeskapi.dto.CategoryDTO;
import com.gustavo.helpdeskapi.entity.Category;
import com.gustavo.helpdeskapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@Valid @RequestBody CategoryCreateDTO dto) {
        return categoryService.createCategory(dto);
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
