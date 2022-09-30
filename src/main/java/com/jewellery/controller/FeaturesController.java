package com.jewellery.controller;

import com.jewellery.model.Product;
import com.jewellery.service.Impl.FeaturesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeaturesController {

    @Autowired
    private FeaturesServiceImpl featuresServiceImpl;

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> search(@PathVariable("keyword") String keyword){
     List<Product> products = featuresServiceImpl.search(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}