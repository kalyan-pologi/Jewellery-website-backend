package com.jewellery.service;

import com.jewellery.model.Product;

import java.util.List;

public interface FeaturesService {

   public List<Product> search(String keyword);
}
