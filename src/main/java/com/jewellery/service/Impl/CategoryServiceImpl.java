package com.jewellery.service.Impl;

import com.jewellery.exceptions.ResourceNotFoundException;
import com.jewellery.model.*;
import com.jewellery.repository.CategoryRepository;
import com.jewellery.repository.ProductRepository;
import com.jewellery.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FeaturesServiceImpl featuresServiceImpl;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<CategoryDto> getAllCategories() throws Exception {
        try {
            List<Category> getCategoryList = categoryRepository.findAll();
            List<Category> categoryList = new ArrayList<>();
            for (Category category : getCategoryList) {
                byte[] image = category.getCategory_image();
                category.setCategory_image(featuresServiceImpl.decompressBytes(image));
                categoryList.add(category);
            }
            List<CategoryDto> catDtos = categoryList.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
                    .collect(Collectors.toList());
            return catDtos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        byte[] image = category.getCategory_image();
        category.setCategory_image(featuresServiceImpl.decompressBytes(image));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto createCategory(CategoryDto createCategory) {
        Category cat = this.modelMapper.map(createCategory, Category.class);
        Category addedCat = this.categoryRepository.save(cat);
        return this.modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategoryById(CategoryDto updateCategory, Integer CategoryId) {
        Category category = categoryRepository.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", CategoryId));
        category.setCategory_id(updateCategory.getCategory_id());
        category.setCategory_desc(updateCategory.getCategory_desc());
        category.setCategory_name(updateCategory.getCategory_name());
        Category updatedcat = this.categoryRepository.save(category);
        return this.modelMapper.map(updatedcat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> deleteCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        categoryRepository.delete(category);
        List<Category> getCategoryList = categoryRepository.findAll();
        List<CategoryDto> catDtos = getCategoryList.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());
        return catDtos;
    }

}
