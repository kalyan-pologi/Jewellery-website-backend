package com.jewellery.controller;

import com.jewellery.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/favorite")
public class FavoritesController {
    //get all favorite products
    @GetMapping("/")
    public List<Product> getAllFavoriteProducts(){
        return null;
    }
    //get single favorite product
    @GetMapping("/product/{id}")
    public Product favoriteProductById( @PathVariable("id") Integer productId){
        return null;
    }
    //delete favorite products
    @DeleteMapping("/product/{id}")
    public List<Product> DeleteFavoriteProductById( @PathVariable("id") Integer productId){
        return null;
    }
}
