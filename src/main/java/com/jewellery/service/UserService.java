package com.jewellery.service;

import com.jewellery.model.User;
import com.jewellery.model.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserDto user) throws Exception;
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
