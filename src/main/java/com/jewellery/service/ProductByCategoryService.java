package com.jewellery.service;

import com.jewellery.model.Product;

import java.util.List;

public interface ProductByCategoryService {

    public List<Product> getProductsByCategory(String categoryName);

    //get Product by id
    public Product getProductByIdByCategory(String categoryName , Integer productId );

    //post Product
    public Product createProductByCategory(String categoryName , Product createProduct );

    //update Product by id
    public Product updateProductByIdByCategory(String categoryName , Product updateProduct , Integer productId );

    //delete Product by id
    public  List<Product> deleteProductByIdByCategory(String categoryName , Integer productId );
}
