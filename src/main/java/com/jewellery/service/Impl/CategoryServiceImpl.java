package com.jewellery.service.Impl;

import com.jewellery.exceptions.ResourceNotFoundException;
import com.jewellery.model.Category;
import com.jewellery.model.Product;
import com.jewellery.repository.CategoryRepository;
import com.jewellery.repository.ProductRepository;
import com.jewellery.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> getCategoryList = categoryRepository.findAll();
        return getCategoryList;
    }
    @Override
    public Category getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("category","id", categoryId));
        return category;
    }
    @Override
    public Category createCategory(Category createCategory) {
        categoryRepository.save(createCategory);
//        Category category = categoryRepository.findById(createCategory.getCategory_id()).get();
        return createCategory;
    }
    @Override
    public Category updateCategoryById(Category updateCategory, Integer CategoryId) {
        Category category = categoryRepository.findById(CategoryId).orElseThrow( () -> new ResourceNotFoundException("category","id", CategoryId));
        category.setCategory_id(updateCategory.getCategory_id());
        category.setCategory_desc(updateCategory.getCategory_desc());
        category.setCategory_name(updateCategory.getCategory_name());
        category.setCategory_image(updateCategory.getCategory_image());
        return category;
    }
    @Override
    public List<Category> deleteCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("category","id", categoryId));
        categoryRepository.delete(category);
        List<Category> getCategoryList = categoryRepository.findAll();
        return getCategoryList;
    }

    @Override
    public List<Product> getAllProductsByCategory(int categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        List<Product> products = this.productRepository.findByCategory(category);
        return products;
    }


}
