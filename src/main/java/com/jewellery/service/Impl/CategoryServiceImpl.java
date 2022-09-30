package com.jewellery.service.Impl;

//import com.jewellery.exceptions.ResourceNotFoundException;
import com.jewellery.model.Category;
import com.jewellery.repository.CategoryRepository;
import com.jewellery.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> getCategoryList = categoryRepository.findAll();
        return getCategoryList;
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
//        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("category","id", categoryId));
        return category;
    }

    @Override
    public Category createCategory(Category createCategory) {
        categoryRepository.save(createCategory);
        Category category = categoryRepository.findById(createCategory.getCategory_id()).get();
        return category;
    }

    @Override
    public Category updateCategoryById(Category updateCategory, Integer updateCategoryId) {
        Category category = categoryRepository.findById(updateCategoryId).get();
//        Category category = categoryRepository.findById(updateCategoryId).orElseThrow( () -> new ResourceNotFoundException("category","id", updateCategoryId));
        category.setCategory_id(updateCategory.getCategory_id());
        category.setCategory_desc(updateCategory.getCategory_desc());
        category.setCategory_name(updateCategory.getCategory_name());
        category.setCategory_image(updateCategory.getCategory_image());

        return category;
    }

    @Override
    public List<Category> deleteCategoryById(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
        List<Category> getCategoryList = categoryRepository.findAll();
        return getCategoryList;
    }

}