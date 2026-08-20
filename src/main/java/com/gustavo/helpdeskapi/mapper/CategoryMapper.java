package com.gustavo.helpdeskapi.mapper;

import com.gustavo.helpdeskapi.dto.CategoryCreateDTO;
import com.gustavo.helpdeskapi.dto.CategoryDTO;
import com.gustavo.helpdeskapi.entity.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category){
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }

    public static Category toEntity(CategoryCreateDTO dto) {

        Category category = new Category();

        category.setName(dto.getName());

        return category;
    }

}
