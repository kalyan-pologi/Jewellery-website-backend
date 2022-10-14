package com.jewellery.controller;

import com.jewellery.exceptions.FileResponse;
import com.jewellery.model.Category;
import com.jewellery.model.CategoryDto;
import com.jewellery.model.Product;
import com.jewellery.service.Impl.CategoryServiceImpl;
import com.jewellery.service.Impl.FeaturesServiceImpl;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
public class FeaturesController {
    @Autowired
    private FeaturesServiceImpl featuresServiceImpl;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> search(@PathVariable("keyword") String keyword){
     List<Product> products = featuresServiceImpl.search(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @Value("${project.image}")
    private String path;
    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(
            @RequestParam("image")MultipartFile image
            ) throws IOException {
        String fileName = this.featuresServiceImpl.uploadImage(path,image);
        return new ResponseEntity<>(new FileResponse(fileName,"image is successfully added"),HttpStatus.OK);
    }
    // image upload
    @PostMapping("/upload/{categoryId}")
    public ResponseEntity<CategoryDto> uploadPostImage(@RequestParam("image") MultipartFile image,
                                                       @PathVariable Integer categoryId) throws IOException {
        CategoryDto category = this.categoryServiceImpl.getCategoryById(categoryId);
        String fileName = this.featuresServiceImpl.uploadImage(path, image);
        category.setCategory_name(fileName);
        CategoryDto updateCategory = this.categoryServiceImpl.updateCategoryById(category, categoryId);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }
    //method to serve files
    @GetMapping(value = "/upload/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.featuresServiceImpl.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream())   ;
    }
}