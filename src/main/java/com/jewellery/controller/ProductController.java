package com.jewellery.controller;

import com.jewellery.exceptions.ApiResponse;
import com.jewellery.model.Product;
import com.jewellery.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;
    //get all Products
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productServiceImpl.getProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    //get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(  @PathVariable("id") Integer productId){
        Product product = productServiceImpl.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    //post Product
    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product createProduct){
        Product product = productServiceImpl.createProduct(createProduct);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    //update Product by id
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@Valid @RequestBody Product updateProduct , @PathVariable("id") Integer productId){
        Product product = productServiceImpl.updateProductById(updateProduct,productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    //delete Product by id
    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse> deleteProductById( @PathVariable("id") Integer deleteId){
        List<Product> productList = productServiceImpl.deleteProductById(deleteId);
        return new ResponseEntity<>(new ApiResponse("product deleted Successfully", true), HttpStatus.OK);
    }
}
