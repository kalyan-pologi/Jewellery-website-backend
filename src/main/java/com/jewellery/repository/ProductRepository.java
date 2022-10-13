package com.jewellery.repository;

import com.jewellery.model.Category;
import com.jewellery.model.Product;
import com.jewellery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//    List<Product> findByProductNameContaining(String keyword);
    @Query("select p from Product p where p.product_name like :key")
    List<Product> findByProductNameContaining(@Param("key") String keyword);

    List<Product> findByUser(User user);
    List<Product> findByCategory(Category category);
}
