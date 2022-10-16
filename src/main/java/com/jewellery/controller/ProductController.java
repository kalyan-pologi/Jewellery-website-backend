package com.jewellery.controller;

import com.jewellery.exceptions.ApiResponse;
import com.jewellery.model.Product;
import com.jewellery.model.ProductDto;
import com.jewellery.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins="*")
@RestController
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;
    //get all Products
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productList = productServiceImpl.getProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    //get product by id
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(  @PathVariable("id") Integer productId){
        ProductDto product = productServiceImpl.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    //post Product
    @PostMapping("/{categoryId}/products")
    public ResponseEntity<ProductDto> createProduct(@Valid @PathVariable Integer categoryId , @RequestBody ProductDto createProduct){
        ProductDto product = productServiceImpl.createProduct(categoryId,createProduct);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    //update Product by id
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProductById(@Valid@RequestBody ProductDto updateProduct , @PathVariable("id") Integer productId){
        ProductDto product = productServiceImpl.updateProductById(updateProduct,productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    //delete Product by id
    @DeleteMapping("/products/{id}")
    public  ResponseEntity<ApiResponse> deleteProductById( @PathVariable("id") Integer deleteId){
        List<ProductDto> productList = productServiceImpl.deleteProductById(deleteId);
        return new ResponseEntity<>(new ApiResponse("product deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Integer categoryId) {
        List<ProductDto> products = this.productServiceImpl.getProductsByCategory(categoryId);
        return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
    }

}
