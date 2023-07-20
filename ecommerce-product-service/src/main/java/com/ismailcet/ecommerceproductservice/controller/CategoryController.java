package com.ismailcet.ecommerceproductservice.controller;

import com.ismailcet.ecommerceproductservice.dto.request.CreateCategoryRequest;
import com.ismailcet.ecommerceproductservice.dto.response.CategoryDto;
import com.ismailcet.ecommerceproductservice.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
        return new ResponseEntity<>(
                categoryService.createCategory(createCategoryRequest),
                HttpStatus.CREATED
        );
    }
    @DeleteMapping("/{categoryId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategoryById(@PathVariable("categoryId") Integer id){
        categoryService.deleteCategoryById(id);
    }
    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
