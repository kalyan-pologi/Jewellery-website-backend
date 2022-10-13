package com.jewellery.controller;

import com.jewellery.model.Product;
import com.jewellery.service.Impl.FavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
public class FavoritesController {

    @Autowired
    private FavoriteServiceImpl favoriteServiceImpl;

    @GetMapping("/{userId}/favorite")
    public ResponseEntity<List<Product>> getAllFavoriteProductsByUser(@PathVariable Integer userId) {
        return null;
    }
    @GetMapping("/{userId}/favorite/{productId}")
    public ResponseEntity<List<Product>> deleteFavoriteProduct(@PathVariable Integer productId , @PathVariable Integer userId) {
        return null;
    }
    @GetMapping("/category/{categoryId}/{productId}/{userId}/favorite")
    public ResponseEntity<List<Product>> addFavoriteProductByCategoryByUser(@PathVariable Integer categoryId ,@PathVariable Integer productId , @PathVariable Integer userId) {
        return null;
    }

    @GetMapping("/category/{categoryId}/{productId}/{userId}/unfavorite")
    public ResponseEntity<List<Product>> deleteFavoriteProductByCategoryByUser(@PathVariable Integer categoryId ,@PathVariable Integer productId , @PathVariable Integer userId) {
        return null;
    }

    @GetMapping("products/{productId}/{userId}/favorite")
    public ResponseEntity<List<Product>> addFavoriteProductByUser( @PathVariable Integer productId ,@PathVariable Integer userId) {
        return null;
    }

    @GetMapping("/products/{productId}/{userId}/unfavorite")
    public ResponseEntity<List<Product>> deleteFavoriteProductByUser(@PathVariable Integer productId,@PathVariable Integer userId) {
        return null;
    }








}
