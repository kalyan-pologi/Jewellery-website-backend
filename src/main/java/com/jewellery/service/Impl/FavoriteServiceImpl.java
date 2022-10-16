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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    @Override
    public List<ProductDto> getfavoriteProductsByUser(String userName) throws Exception {


        try{
            User user = userRepository.findByEmail(userName).get();
            int userId = user.getUser_id();
            List<Product> products = productRepository.findProductsByUsersId(userId);
            List<ProductDto> productDtos = products.stream().map((pro) -> this.modelMapper.map(pro, ProductDto.class))
                    .collect(Collectors.toList());
            return productDtos;
        }catch (Exception e){
            throw new Exception(e);
        }

    }


    @Override
    public String addFavoriteProductByUser(String userName, Integer productId) {
        User user1 = userRepository.findByEmail(userName).get();
        Product product = productRepository.findById(productId).orElseThrow( () -> new ResourceNotFoundException("product","id",productId));
        Set<Product> userProducts = user1.getProducts();
        userProducts.add(product);
        productRepository.save(product);
        userRepository.save(user1);
        return "added to favorite";

    }



    @Override
    public void deleteFavoriteProductByUser(String userName, Integer productId) throws Exception {

   try{
       User user = userRepository.findByEmail(userName).get();
       int userId = user.getUser_id();
       productRepository.deleteFavoriteProductByUser(userId,productId);
   }catch (Exception e){
       throw new Exception(e);
   }
    }
}
