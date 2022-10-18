package com.jewellery.service.Impl;

import com.jewellery.exceptions.ResourceNotFoundException;
import com.jewellery.model.*;
import com.jewellery.repository.CategoryRepository;
import com.jewellery.repository.ProductRepository;
import com.jewellery.repository.UserRepository;
import com.jewellery.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FeaturesServiceImpl featuresServiceImpl;
    @Override
    public List<ProductDto> getProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtos = productList.stream().map((post) -> this.modelMapper.map(post, ProductDto.class))
                .collect(Collectors.toList());
        return productDtos;
    }
    @Override
    public ProductDto getProductById(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow( () -> new ResourceNotFoundException("product","id", productId));
        byte[] image = product.getProduct_image();
        product.setProduct_image(featuresServiceImpl.decompressBytes(image));
        return this.modelMapper.map(product, ProductDto.class);
    }


    @Override
    public ProductDto createProduct(Integer categoryId , Integer imageId,  ProductDto createProduct) {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("category","id", categoryId));
        Product product = this.modelMapper.map(createProduct,Product.class);
        product.setCategory(category);;
        Product newProduct = productRepository.save(product);
        return this.modelMapper.map(newProduct,ProductDto.class);
    }
    @Override
    public ProductDto updateProductById(ProductDto updateProduct, Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow( () -> new ResourceNotFoundException("product","id", productId));
        product.setProduct_id(updateProduct.getProduct_id());
        product.setProduct_name(updateProduct.getProduct_name());
        product.setProduct_desc(updateProduct.getProduct_desc());
        Product updatedProduct = productRepository.save(product);
        return this.modelMapper.map(updatedProduct, ProductDto.class);
    }
    @Override
    public  List<ProductDto> deleteProductById(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow( () -> new ResourceNotFoundException("product","id", productId));
        productRepository.delete(product);
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtos = productList.stream().map((post) -> this.modelMapper.map(post, ProductDto.class))
                .collect(Collectors.toList());
        return productDtos;

    }

    @Override
    public List<ProductDto> getProductsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("category","id", categoryId));
        List<Product> products = productRepository.findByCategory(category);
        List<ProductDto> productDtos = products.stream().map((post) -> this.modelMapper.map(post, ProductDto.class))
                .collect(Collectors.toList());
        return productDtos;
    }
}
