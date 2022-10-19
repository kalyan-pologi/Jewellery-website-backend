package com.jewellery.service;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FeaturesService {

    ResponseEntity<String> uploadImageByCategoryId (MultipartFile file,Integer categoryId) throws IOException;
    ResponseEntity<String> uploadImageByProductId (MultipartFile file,Integer productId) throws IOException;
}
