package com.jewellery.repository;

import com.jewellery.model.Category;
import com.jewellery.model.Product;
import com.jewellery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//    List<Product> findByProductNameContaining(String keyword);

//    @Query("select p from Product p where p.product_name like :key")
//    List<Product> findByProductNameContaining(@Param("key") String keyword);

//    @Query("select p from Product p where p.category_id = :category_id")
//    List<Product> findByCategoryId(Integer category_id);

    List<Product> findByCategory(Category category);

    List<Product> findByUsers(User user);

//    @Query("" SELECT product.product_id, product.product_desc, product.product_image, product.product_name FROM product_users JOIN product ON product_users.products_product_id = product.product_id where product_users.users_user_id = : userId ")
    @Query(value= "select\n" +
            "  PRODUCT.PRODUCT_ID,\n" +
            "  PRODUCT.PRODUCT_DESC,\n" +
            "  PRODUCT.PRODUCT_IMAGE,\n" +
            "  PRODUCT.PRODUCT_NAME,\n" +
            "  PRODUCT.CATEGORY_ID\n" +
            "from PRODUCT_USERS\n" +
            "  join PRODUCT\n" +
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
//    @Query("delete FROM PRODUCT_USERS where PRODUCT_USERS.USER_ID= :userId and PRODUCT_USERS.PRODUCT_ID = :productId")
    void deleteFavoriteProductByUser(@Param("userId") Integer user_Id , @Param("productId") Integer product_id);

}
