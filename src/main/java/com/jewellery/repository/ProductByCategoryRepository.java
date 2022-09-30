package com.jewellery.repository;

import com.jewellery.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductByCategoryRepository extends JpaRepository<Product , Integer> {


//    List<Product> getByCategory(String categoryName);

//    @Query("select p from product where category_name= :categoryName")
//    List<Product> getByCategory(String categoryName);


}
