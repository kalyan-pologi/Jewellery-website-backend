package com.jewellery.service;

import com.jewellery.model.User;

import java.util.List;

public interface UserService {
    User registerNewUser(User user);
    User createUser(User user);
    User updateUser(User user, Integer userId);
    User getUserById(Integer userId);
    List<User> getAllUsers();
    void deleteUser(Integer userId);
}
