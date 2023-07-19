package com.ismailcet.ecommerceproductservice.controller;

import com.ismailcet.ecommerceproductservice.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
