package com.jewellery.service.Impl;

import com.jewellery.exceptions.ResourceNotFoundException;
import com.jewellery.model.Category;

import com.jewellery.model.Product;
import com.jewellery.repository.CategoryRepository;
import com.jewellery.repository.ProductRepository;
import com.jewellery.service.FeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> uploadImageByCategoryId(MultipartFile file , Integer categoryId) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("category","id", categoryId));
        category.setCategory_image(compressBytes(file.getBytes()));
        categoryRepository.save(category);
        return new ResponseEntity<>("uploaded" , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> uploadImageByProductId(MultipartFile file, Integer productId) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Product product = productRepository.findById(productId).orElseThrow( () -> new ResourceNotFoundException("product","id", productId));
        product.setProduct_image(compressBytes(file.getBytes()));
        productRepository.save(product);
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
    public byte[] compressBytes(byte[] data) {
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

    public byte[] decompressBytes(byte[] data) {
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
