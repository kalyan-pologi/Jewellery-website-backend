package com.jewellery.controller;

import com.jewellery.service.Impl.FeaturesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins="*")
@RestController
public class FeaturesController {

    @Autowired
    private FeaturesServiceImpl featuresServiceImpl;


    @PostMapping(value = "/upload/category/{categoryId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadImageByCategoryId(@RequestParam("image") MultipartFile file , @PathVariable("categoryId") Integer categoryId) throws IOException {
        return featuresServiceImpl.uploadImageByCategoryId(file,categoryId);
    }
    @PostMapping(value = "/upload/product/{productId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadImageByProductId(@RequestParam("image") MultipartFile file, @PathVariable("productId") Integer productId) throws IOException {
        return featuresServiceImpl.uploadImageByProductId(file,productId);
    }

//    @GetMapping(value = "/get/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
//    public CategoryDto getImageByCategoryId(@PathVariable("id") int id) throws IOException {
//        CategoryDto categoryDto = featuresServiceImpl.getImageByCategoryId(id);
//       return categoryDto;
//    }


}