package com.gustavo.helpdeskapi.dto;

public class CategoryCreateDTO {

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