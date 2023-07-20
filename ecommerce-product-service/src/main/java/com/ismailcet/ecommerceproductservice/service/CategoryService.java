package com.ismailcet.ecommerceproductservice.service;

import com.ismailcet.ecommerceproductservice.dto.converter.CategoryDtoConverter;
import com.ismailcet.ecommerceproductservice.dto.request.CreateCategoryRequest;
import com.ismailcet.ecommerceproductservice.dto.response.CategoryDto;
import com.ismailcet.ecommerceproductservice.entity.Category;
import com.ismailcet.ecommerceproductservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto createCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = Category.builder()
                .name(createCategoryRequest.getName())
                .build();
        categoryRepository.save(category);
        return CategoryDtoConverter.converter(category);
    }

    public void deleteCategoryById(Integer id) {
        Category category =
                categoryRepository.findById(id).orElseThrow(()-> new NullPointerException("Category Id does not exist ! "));

        categoryRepository.deleteById(id);
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDtoConverter::converter)
                .collect(Collectors.toList());
    }
}
