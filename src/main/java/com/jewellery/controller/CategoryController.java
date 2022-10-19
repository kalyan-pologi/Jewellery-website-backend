package com.jewellery.controller;


import com.jewellery.exceptions.ApiResponse;;
import com.jewellery.model.CategoryDto;
import com.jewellery.service.Impl.CategoryServiceImpl;
import com.jewellery.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    //get all categories
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() throws Exception {
        List<CategoryDto> categoryList = categoryServiceImpl.getAllCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
    //get category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById( @PathVariable("id") Integer categoryId){
        CategoryDto category = categoryServiceImpl.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody CategoryDto createCategory){
        CategoryDto category = categoryServiceImpl.createCategory(createCategory);
        return new ResponseEntity<>(category,HttpStatus.CREATED);
    }
    //update category by id
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@Valid @RequestBody CategoryDto updateCategory , @PathVariable("id") Integer categoryId){
        CategoryDto category = categoryServiceImpl.updateCategoryById(updateCategory,categoryId);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    //delete category by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById( @PathVariable("id") Integer categoryId){
        List<CategoryDto> category = categoryServiceImpl.deleteCategoryById(categoryId);
        return new ResponseEntity<>(new ApiResponse("category deleted Successfully", true), HttpStatus.OK);
    }

}
