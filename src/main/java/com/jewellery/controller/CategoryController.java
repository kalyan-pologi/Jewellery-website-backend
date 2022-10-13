package com.jewellery.controller;


import com.jewellery.exceptions.ApiResponse;
import com.jewellery.model.Category;
import com.jewellery.model.Product;
import com.jewellery.service.Impl.CategoryServiceImpl;
import com.jewellery.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categoryList = categoryServiceImpl.getAllCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
    //get category by id
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById( @PathVariable("id") Integer categoryId){
        Category category = categoryServiceImpl.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    //post category
    @PostMapping("/")
    public ResponseEntity<Category> createCategory(@Valid  @RequestBody Category createCategory ){
        Category category = categoryServiceImpl.createCategory(createCategory);
        return new ResponseEntity<>(category,HttpStatus.CREATED);
    }
//    @PostMapping("/")
//    public ResponseEntity<Category> createCategory(@Valid @RequestParam("image") MultipartFile image, @RequestBody Category createCategory ){
//        Category category = categoryServiceImpl.createCategory(createCategory);
//        return new ResponseEntity<>(category,HttpStatus.CREATED);
//    }
    //update category by id
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategoryById(@Valid @RequestBody Category updateCategory , @PathVariable("id") Integer categoryId){
        Category category = categoryServiceImpl.updateCategoryById(updateCategory,categoryId);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    //delete category by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById( @PathVariable("id") Integer categoryId){
        List<Category> category = categoryServiceImpl.deleteCategoryById(categoryId);
        return new ResponseEntity<>(new ApiResponse("category deleted Successfully", true), HttpStatus.OK);
    }



    // get all products by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getALlProductsByCategory(@PathVariable Integer categoryId) {
        List<Product> products = this.categoryServiceImpl.getAllProductsByCategory(categoryId);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

}
