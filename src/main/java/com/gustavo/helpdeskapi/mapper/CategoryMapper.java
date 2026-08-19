package com.gustavo.helpdeskapi.mapper;

import com.gustavo.helpdeskapi.dto.CategoryDTO;
import com.gustavo.helpdeskapi.entity.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category){
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }

}
