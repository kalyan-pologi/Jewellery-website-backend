package com.jewellery.service.Impl;

import com.jewellery.model.Product;

import com.jewellery.service.FavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Override
    public List<Product> getAllFavoriteProducts() {
        return null;
    }
    @Override
    public Product getFavoriteProductById(Integer productId) {
        return null;
    }
    @Override
    public List<Product> DeleteFavoriteProductById() {
        return null;
    }
}
