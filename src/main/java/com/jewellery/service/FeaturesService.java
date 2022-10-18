package com.jewellery.service;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FeaturesService {

//   public List<Product> search(String keyword);
ResponseEntity<String> uploadImageByCategoryId (MultipartFile file) throws IOException;
//   CategoryDto getImageByCategoryId (int id) throws FileNotFoundException;
}
