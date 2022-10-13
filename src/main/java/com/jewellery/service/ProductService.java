package com.jewellery.service;

import com.jewellery.model.Category;
import com.jewellery.model.Product;
import com.jewellery.model.ProductDto;

import java.util.List;

public interface ProductService {

    //get all Products
    public List<Product> getProducts();

    //get Product by id
    public Product getProductById(Integer productId);

    //post Product
    public ProductDto createProduct(Integer categoryId , Product createProduct);

    //update Product by id
    public Product updateProductById(Product updateProduct , Integer productId);

    //delete Product by id
    public  List<Product> deleteProductById(Integer productId);

    List<Product> getProductsByCategory(Integer categoryId);

    List<Product> getProductsByUser(Integer userId);
}
