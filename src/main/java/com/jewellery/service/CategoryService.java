package com.jewellery.service;

import com.jewellery.model.Category;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface CategoryService{

    //get all categories
    public List<Category> getAllCategories();

    //get category by id
    public Category getCategoryById(Integer categoryId);

    //post category
    public Category createCategory(Category createCategory);

    //update category by id
    public Category updateCategoryById(Category updateCategory, Integer updateCategoryId);

    //delete category by id
    public List<Category> deleteCategoryById(Integer category_id);
}
