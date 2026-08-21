package com.gustavo.helpdeskapi.service;

import com.gustavo.helpdeskapi.dto.CategoryCreateDTO;
import com.gustavo.helpdeskapi.dto.CategoryDTO;
import com.gustavo.helpdeskapi.entity.Category;
import com.gustavo.helpdeskapi.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;


    @Test
    void shouldCreateCategory() {

        CategoryCreateDTO dto = new CategoryCreateDTO(
                "Hardware"
        );

        Category savedCategory = new Category();

        savedCategory.setId(1L);
        savedCategory.setName("Hardware");

        when(categoryRepository.save(any(Category.class)))
                .thenReturn(savedCategory);

        CategoryDTO result = categoryService.createCategory(dto);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Hardware", result.getName());

        verify(categoryRepository).save(any(Category.class));
    }


    @Test
    void shouldFindAllCategories() {

        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Hardware");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Software");

        when(categoryRepository.findAll())
                .thenReturn(List.of(category1, category2));

        List<CategoryDTO> result = categoryService.getAllCategories();

        Assertions.assertEquals(2, result.size());

        Assertions.assertEquals(1L, result.get(0).getId());
        Assertions.assertEquals("Hardware", result.get(0).getName());

        Assertions.assertEquals(2L, result.get(1).getId());
        Assertions.assertEquals("Software", result.get(1).getName());

        verify(categoryRepository).findAll();
    }
}