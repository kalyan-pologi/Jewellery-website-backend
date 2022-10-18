package com.jewellery.service.Impl;

import com.jewellery.exceptions.ResourceNotFoundException;
import com.jewellery.model.Category;

import com.jewellery.repository.CategoryRepository;
import com.jewellery.repository.ProductRepository;
import com.jewellery.service.FeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class FeaturesServiceImpl implements FeaturesService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<String> uploadImageByCategoryId(MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
//        ImageModel img = new ImageModel(
//                compressBytes(file.getBytes()));
//        Category category = new Category(compressBytes(file.getBytes()));
        Category category = categoryRepository.findById(1000).orElseThrow( () -> new ResourceNotFoundException("category","id", 1000));
        category.setCategory_image(compressBytes(file.getBytes()));
        categoryRepository.save(category);
//                imageRepository.save(img);
        return new ResponseEntity<>("uploaded" , HttpStatus.OK);
    }

//    @Override
//    public CategoryDto getImageByCategoryId(int id) throws FileNotFoundException {
//        final Optional<ImageModel> retrievedImage = imageRepository.findById(id);
//        ImageModel img = new ImageModel(
//                decompressBytes(retrievedImage.get().getPicByte()));
//        return img;
//    }


    // compress the image bytes before storing it in the database


    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    @Bean
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {

            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
