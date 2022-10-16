package com.jewellery.service;

import com.jewellery.model.Category;
import com.jewellery.model.Product;
import com.jewellery.model.ProductDto;

import java.util.List;

public interface ProductService {

    //get all Products
    public List<ProductDto> getProducts();

    //get Product by id
    public ProductDto getProductById(Integer productId);

    //post Product
    public ProductDto createProduct(Integer categoryId , ProductDto createProduct);

    //update Product by id
    public ProductDto updateProductById(ProductDto updateProduct , Integer productId);

    //delete Product by id
    public  List<ProductDto> deleteProductById(Integer productId);

    List<ProductDto> getProductsByCategory(Integer categoryId);

}
