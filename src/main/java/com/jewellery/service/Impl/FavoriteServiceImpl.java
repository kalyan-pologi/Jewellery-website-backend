package com.jewellery.service.Impl;

import com.jewellery.exceptions.ResourceNotFoundException;
import com.jewellery.model.Product;

import com.jewellery.model.ProductDto;
import com.jewellery.model.User;
import com.jewellery.repository.ProductRepository;
import com.jewellery.repository.UserRepository;
import com.jewellery.service.FavoriteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FeaturesServiceImpl featuresServiceImpl;

    @Override
    public List<ProductDto> getFavoriteProductsByUser(String userName) throws Exception {
        try {
            User user = userRepository.findByEmail(userName).orElseThrow( () -> new ResourceNotFoundException("user","userName", userName));
            int userId = user.getUser_id();
            List<Product> products = productRepository.findProductsByUsersId(userId);
            List<Product> productList = new ArrayList<>();
            for(Product product : products){
                byte[] image = product.getProduct_image();
                product.setProduct_image(featuresServiceImpl.decompressBytes(image));
                productList.add(product);
            }
            List<ProductDto> productDtos = productList.stream().map((pro) -> this.modelMapper.map(pro, ProductDto.class))
                    .collect(Collectors.toList());
            return productDtos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean isProductFavoriteByProductId(String userName, Integer productId) {
        User user = userRepository.findByEmail(userName).orElseThrow( () -> new ResourceNotFoundException("user","userName", userName));
        int userId = user.getUser_id();
        int ans = productRepository.isProductExistsById(userId,productId);
        return (ans == 0) ? false : true;
    }

    @Override
    public String addFavoriteProductByUser(String userName, Integer productId) {
        User user1 = userRepository.findByEmail(userName).orElseThrow( () -> new ResourceNotFoundException("user","userName", userName));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product", "id", productId));
        Set<Product> userProducts = user1.getProducts();
        userProducts.add(product);
        productRepository.save(product);
        userRepository.save(user1);
        return "added to favorite";
    }


    @Override
    public void deleteFavoriteProductByUser(String userName, Integer productId) throws Exception {
        try {
            User user = userRepository.findByEmail(userName).orElseThrow( () -> new ResourceNotFoundException("user","userName", userName));
            int userId = user.getUser_id();
            productRepository.deleteFavoriteProductByUser(userId, productId);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
