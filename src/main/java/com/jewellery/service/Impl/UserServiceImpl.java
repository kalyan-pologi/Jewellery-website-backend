package com.jewellery.service.Impl;

import com.jewellery.config.CustomUserDetailServiceImpl;
import com.jewellery.exceptions.ResourceNotFoundException;
import com.jewellery.model.User;
import com.jewellery.repository.UserRepository;
import com.jewellery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailServiceImpl customUserDetailService;
    @Override
    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<User> user = users.stream().map(e -> e).collect(Collectors.toList());
        return user;
    }
    @Override
    public User getUserById(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
        return user;
    }
    @Override
    public User createUser(User user) {
        User savedUser = this.userRepository.save(user);
        return savedUser;
    }
    @Override
    public User updateUser(User user, Integer userId) {
        User userById = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
        user.setUser_name(user.getUser_name());
        user.setUser_email(user.getUser_email());
        user.setUser_password(user.getUser_password());
        User updatedUser = this.userRepository.save(user);
        return updatedUser;
    }
    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepository.delete(user);
    }
    @Override
    public User registerNewUser(User user) throws Exception {
            UserDetails userDetails = customUserDetailService.loadUserByUsername(user.getUser_email());
            if(userDetails.getUsername().equals(user.getUser_email())){
                 throw new Exception("user already exits...please login");
            }

        // encoded the password
        user.setUser_password(this.passwordEncoder.encode(user.getUser_password()));
        // add roles if needed
        User newUser = this.userRepository.save(user);

        return newUser;
    }
}
