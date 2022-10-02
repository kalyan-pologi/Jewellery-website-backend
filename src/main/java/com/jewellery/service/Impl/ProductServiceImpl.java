package com.jewellery.service.Impl;

import com.jewellery.exceptions.ResourceNotFoundException;
import com.jewellery.model.Category;
import com.jewellery.model.Product;
import com.jewellery.repository.ProductRepository;
import com.jewellery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow( () -> new ResourceNotFoundException("product","id", productId));
        return product;
    }

    @Override
    public Product createProduct(Product createProduct) {
        productRepository.save(createProduct);
//        Product product = productRepository.findById(createProduct.getProduct_id()).get();
        return createProduct;
    }

    @Override
    public Product updateProductById(Product updateProduct, Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow( () -> new ResourceNotFoundException("product","id", productId));
        product.setProduct_id(updateProduct.getProduct_id());
        product.setProduct_name(updateProduct.getProduct_name());
        product.setProduct_desc(updateProduct.getProduct_desc());
        product.setProduct_image(updateProduct.getProduct_image());

        return product;
    }

    @Override
    public  List<Product> deleteProductById(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow( () -> new ResourceNotFoundException("product","id", productId));
        productRepository.delete(product);
        List<Product> productList = productRepository.findAll();
        return productList;
    }
}
