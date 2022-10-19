package com.jewellery.repository;

import com.jewellery.model.Category;
import com.jewellery.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findByCategory(Category category);

    @Query(value= "select\n" +
            "  product.product_id,\n" +
            "  product.product_desc,\n" +
            "  product.product_name,\n" +
            "  product.product_image,\n" +
            "  product.category_id\n" +
            "from product\n" +
            "  join product_users\n" +
            "    on product_users.product_id = product.product_id\n" +
            "where product_users.user_id = :userId", nativeQuery=true)
    List<Product> findProductsByUsersId(@Param("userId") Integer userId);


    @Modifying
    @Transactional
    @Query(value = "delete from product_users\n" +
            "where (\n" +
            "  product_users.user_id = :userId\n" +
            "  and product_users.product_id = :productId\n" +
            ")", nativeQuery=true)
    void deleteFavoriteProductByUser(@Param("userId") Integer user_Id , @Param("productId") Integer product_id);


    @Query(value = "select exists (\n" +
            "  select *\n" +
            "  from product_users\n" +
            "  where (\n" +
            "    product_users.user_id = :userId\n" +
            "    and product_users.product_id = :productId\n" +
            "  )\n" +
            ")",nativeQuery = true)
    int isProductExistsById(@Param("userId")  Integer userId, @Param("productId") Integer productId);
}
