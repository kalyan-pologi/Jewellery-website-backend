package com.jewellery.controller;

import com.jewellery.exceptions.ApiResponse;
import com.jewellery.model.Product;
import com.jewellery.service.Impl.ProductByCategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/{category}/product")
public class ProductByCategoryController {

    private ProductByCategoryServiceImpl productByCategoryService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable("category") String categoryName){
        List<Product> productList = productByCategoryService.getProductsByCategory(categoryName);
        return ResponseEntity.ok(productList);
    }
    //get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById( @PathVariable("category") String categoryName , @PathVariable("id") Integer productId ){
        Product product = productByCategoryService.getProductByIdByCategory(categoryName , productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //post Product
    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@Valid @PathVariable("category") String categoryName , @RequestBody Product createProduct){
        Product product = productByCategoryService.createProductByCategory(categoryName , createProduct);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    //update Product by id
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@Valid @PathVariable("category") String categoryName , @RequestBody Product updateProduct , @PathVariable("id") Integer productId){
        Product product = productByCategoryService.updateProductByIdByCategory(categoryName , updateProduct,productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //delete Product by id
    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse> deleteProductById( @PathVariable("category") String categoryName , @PathVariable("id") Integer deleteId){
        List<Product> productList = productByCategoryService.deleteProductByIdByCategory(categoryName , deleteId);
        return new ResponseEntity<>(new ApiResponse("product deleted Successfully", true), HttpStatus.OK);
    }
}
