package com.jewellery.service.Impl;

import com.jewellery.model.Product;
import com.jewellery.repository.ProductRepository;
import com.jewellery.service.FeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeaturesServiceImpl implements FeaturesService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> search(String keyword) {
        List<Product> products = productRepository.findByProductNameContaining(keyword);
        return products;
    }
}
