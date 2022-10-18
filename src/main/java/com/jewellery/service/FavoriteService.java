package com.jewellery.service;

import com.jewellery.model.ProductDto;

import java.util.List;

public interface FavoriteService {


    List<ProductDto> getFavoriteProductsByUser(String userName) throws Exception;

   String addFavoriteProductByUser(String userName, Integer productId);


    void deleteFavoriteProductByUser(String userName, Integer productId) throws Exception;

    Boolean isProductFavoriteByProductId(String userName, Integer productId);
}
