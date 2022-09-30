package com.jewellery.service;

import com.jewellery.model.Product;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface FavoriteService {

    //get all favorite products
    public List<Product> getAllFavoriteProducts();

    //get favorite products by id
    public Product getFavoriteProductById(Integer productId);

    //delete favorite products
    public List<Product> DeleteFavoriteProductById();

}
