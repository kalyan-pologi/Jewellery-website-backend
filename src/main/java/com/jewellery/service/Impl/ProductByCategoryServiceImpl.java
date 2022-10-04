package com.jewellery.service.Impl;

import com.jewellery.model.Product;
import com.jewellery.repository.ProductByCategoryRepository;
import com.jewellery.service.ProductByCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductByCategoryServiceImpl implements ProductByCategoryService {
    @Autowired
    private ProductByCategoryRepository productByCategoryRepository;
    @Override
    public List<Product> getProductsByCategory(String categoryName) {
//        List<Product> productList = productByCategoryRepository.getByCategory(categoryName);
//        return productList;
        return null;
    }
    @Override
    public Product getProductByIdByCategory(String categoryName , Integer productId) {
        return null;
    }
    @Override
    public Product createProductByCategory(String categoryName , Product createProduct) {
        return null;
    }
    @Override
    public Product updateProductByIdByCategory(String categoryName , Product updateProduct, Integer productId) {
        return null;
    }
    @Override
    public List<Product> deleteProductByIdByCategory(String categoryName , Integer productId) {
        return null;
    }
}
