package com.gustavo.helpdeskapi.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryCreateDTO {

    @NotBlank(message = "Nome da categoria é obrigatório")
    private String name;

    public CategoryCreateDTO() {
    }

    public CategoryCreateDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}