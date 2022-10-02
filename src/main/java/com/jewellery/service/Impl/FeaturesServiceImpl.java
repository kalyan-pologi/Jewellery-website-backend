package com.jewellery.service.Impl;

import com.jewellery.model.Product;
import com.jewellery.repository.ProductRepository;
import com.jewellery.service.FeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FeaturesServiceImpl implements FeaturesService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> search(String keyword) {
        List<Product> products = productRepository.findByProductNameContaining(keyword);
        return products;
    }


//    @Override
//    public String uploadImage(String path, MultipartFile file) throws IOException {
//
//        //file name
//        String name = file.getOriginalFilename();
//        //generate random name
//        String randomID = UUID.randomUUID().toString();
//        String fileName = randomID.concat(name.substring(name.lastIndexOf('.')));
//        //full path
//        String filePath = path+ File.separator + fileName;
//        //create folder if not created
//        File f = new File(path);
//        if(!f.exists()){
//            f.mkdir();
//        }
//        //copy file
//        Files.copy(file.getInputStream(), Paths.get(filePath));
//        return fileName;
//    }
//
//    @Override
//    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
//        String fullPath = path + File.separator + fileName;
//        InputStream is = new FileInputStream(fullPath);
//        return is;
//    }
}
