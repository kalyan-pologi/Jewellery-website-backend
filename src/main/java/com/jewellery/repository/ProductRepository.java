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
            "  PRODUCT.PRODUCT_ID,\n" +
            "  PRODUCT.PRODUCT_DESC,\n" +
            "  PRODUCT.PRODUCT_IMAGE,\n" +
            "  PRODUCT.PRODUCT_NAME,\n" +
            "  PRODUCT.CATEGORY_ID\n" +
            "from PRODUCT\n" +
            "  join PRODUCT_USERS\n" +
            "    on PRODUCT_USERS.PRODUCT_ID = PRODUCT.PRODUCT_ID\n" +
            "where PRODUCT_USERS.USER_ID = :userId", nativeQuery=true)
    List<Product> findProductsByUsersId(@Param("userId") Integer userId);


    @Modifying
    @Transactional
    @Query(value = "delete from PRODUCT_USERS\n" +
            "where (\n" +
            "  PRODUCT_USERS.USER_ID = :userId\n" +
            "  and PRODUCT_USERS.PRODUCT_ID = :productId\n" +
            ")", nativeQuery=true)
    void deleteFavoriteProductByUser(@Param("userId") Integer user_Id , @Param("productId") Integer product_id);


    @Query(value = "select exists (\n" +
            "  select *\n" +
            "  from PRODUCT_USERS\n" +
            "  where (\n" +
            "    PRODUCT_USERS.USER_ID = :userId\n" +
            "    and PRODUCT_USERS.PRODUCT_ID = :productId\n" +
            "  )\n" +
            ")",nativeQuery = true)
    int isProductExistsById(@Param("userId")  Integer userId, @Param("productId") Integer productId);
}
