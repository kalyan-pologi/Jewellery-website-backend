package com.jewellery.service;

import com.jewellery.model.Category;
import com.jewellery.model.CategoryDto;
import com.jewellery.model.Product;
import com.jewellery.model.ProductDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface CategoryService{

    //get all categories
    public List<CategoryDto> getAllCategories();

    //get category by id
    public CategoryDto getCategoryById(Integer categoryId);

    //post category
    public CategoryDto createCategory(CategoryDto createCategory);

    //update category by id
    public CategoryDto updateCategoryById(CategoryDto updateCategory, Integer updateCategoryId);

    //delete category by id
    public List<CategoryDto> deleteCategoryById(Integer category_id);


}
