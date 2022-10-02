package com.jewellery.service;

import com.jewellery.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FeaturesService {

   public List<Product> search(String keyword);

//   String uploadImage (String path, MultipartFile file) throws IOException;
//
//   InputStream getResource (String path, String fileName) throws FileNotFoundException;
}
