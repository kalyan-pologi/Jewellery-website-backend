package com.jewellery.service;

import com.jewellery.model.Product;
import com.jewellery.model.ProductDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FavoriteService {


    List<ProductDto> getfavoriteProductsByUser(String userName) throws Exception;

   String addFavoriteProductByUser(String userName, Integer productId);

//    String addFavoriteProductsByUser(Integer userId, @RequestBody Product product);

    void deleteFavoriteProductByUser(String userName, Integer productId) throws Exception;
}
