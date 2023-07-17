package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private int id;
    private String name;
    private String description;
}
