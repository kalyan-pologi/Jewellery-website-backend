package com.jewellery.service;

import com.jewellery.model.CategoryDto;

import java.util.List;

public interface CategoryService{

    //get all categories
     List<CategoryDto> getAllCategories();

    //get category by id
     CategoryDto getCategoryById(Integer categoryId);

    //post category
     CategoryDto createCategory(CategoryDto createCategory);

    //update category by id
     CategoryDto updateCategoryById(CategoryDto updateCategory, Integer updateCategoryId);

    //delete category by id
     List<CategoryDto> deleteCategoryById(Integer category_id);


}
