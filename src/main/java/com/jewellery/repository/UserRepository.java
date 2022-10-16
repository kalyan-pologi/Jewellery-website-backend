package com.jewellery.repository;

import com.jewellery.model.Product;
import com.jewellery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.user_email = :email")
    Optional<User> findByEmail(String email);

//    List<User> findUsersByProductsId(Integer product_id);

//    boolean existsByUsersId(Integer userId);

}
