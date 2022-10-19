package com.jewellery.service;

import com.jewellery.model.ProductDto;

import java.util.List;

public interface ProductService {

    //get all Products
     List<ProductDto> getProducts();

    //get Product by id
     ProductDto getProductById(Integer productId);

    //post Product
     ProductDto createProduct(Integer categoryId ,  ProductDto createProduct);

    //update Product by id
     ProductDto updateProductById(ProductDto updateProduct , Integer productId);

    //delete Product by id
      List<ProductDto> deleteProductById(Integer productId);

    List<ProductDto> getProductsByCategory(Integer categoryId);

}
