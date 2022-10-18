package com.jewellery.controller;

import com.jewellery.model.ProductDto;
import com.jewellery.service.Impl.FavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
public class FavoritesController {

    @Autowired
    private FavoriteServiceImpl favoriteServiceImpl;


    @GetMapping("/user/{userName}/favorite")
    public ResponseEntity<List<ProductDto>> getAllFavoriteProductsByUser(@PathVariable String userName) throws Exception {


        List<ProductDto> products = this.favoriteServiceImpl.getFavoriteProductsByUser(userName);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/user/{userName}/{productId}")
    public ResponseEntity<Boolean> isProductFavoriteByProductId(@PathVariable String userName ,@PathVariable Integer productId) throws Exception {
        Boolean flag = this.favoriteServiceImpl.isProductFavoriteByProductId(userName,productId);
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    @PostMapping("/user/{userName}/{productId}/add-favorite")
    public ResponseEntity<String> addFavoriteProductByUser(@PathVariable String userName , @PathVariable Integer productId) {
        String added = this.favoriteServiceImpl.addFavoriteProductByUser(userName,productId);
        return new ResponseEntity<>(added, HttpStatus.OK);

    }
    @PostMapping("/user/{userName}/{productId}/un-favorite")
    public void deleteFavoriteProductByUser(@PathVariable String userName, @PathVariable Integer productId) throws Exception {
        try {
            this.favoriteServiceImpl.deleteFavoriteProductByUser(userName, productId);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

















//    @GetMapping("/{userId}/favorite")
//    public ResponseEntity<List<Product>> getAllFavoriteProductsByUser(@PathVariable Integer userId) {
//        return null;
//    }
//    @GetMapping("/{userId}/favorite/{productId}")
//    public ResponseEntity<List<Product>> deleteFavoriteProduct(@PathVariable Integer productId , @PathVariable Integer userId) {
//        return null;
//    }
//    @GetMapping("/category/{categoryId}/{productId}/{userId}/favorite")
//    public ResponseEntity<List<Product>> addFavoriteProductByCategoryByUser(@PathVariable Integer categoryId ,@PathVariable Integer productId , @PathVariable Integer userId) {
//        return null;
//    }
//
//    @GetMapping("/category/{categoryId}/{productId}/{userId}/unfavorite")
//    public ResponseEntity<List<Product>> deleteFavoriteProductByCategoryByUser(@PathVariable Integer categoryId ,@PathVariable Integer productId , @PathVariable Integer userId) {
//        return null;
//    }
//
//    @GetMapping("products/{productId}/{userId}/favorite")
//    public ResponseEntity<List<Product>> addFavoriteProductByUser( @PathVariable Integer productId ,@PathVariable Integer userId) {
//        return null;
//    }
//
//    @GetMapping("/products/{productId}/{userId}/unfavorite")
//    public ResponseEntity<List<Product>> deleteFavoriteProductByUser(@PathVariable Integer productId,@PathVariable Integer userId) {
//        return null;
//    }

}
