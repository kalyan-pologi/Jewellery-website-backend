package com.jewellery.service.Impl;

import com.jewellery.config.CustomUserDetailServiceImpl;
import com.jewellery.exceptions.ResourceNotFoundException;
import com.jewellery.model.CategoryDto;
import com.jewellery.model.User;
import com.jewellery.model.UserDto;
import com.jewellery.repository.UserRepository;
import com.jewellery.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomUserDetailServiceImpl customUserDetailService;

    public UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    @Override
    public List<UserDto> getAllUsers() throws Exception {
        try {
            List<User> users = this.userRepository.findAll();
            List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
            return userDtos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }
    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
        User userById = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
        userById.setUser_name(user.getUser_name());
        userById.setUser_email(user.getUser_email());
        userById.setUser_password(user.getUser_password());
        User updatedUser = this.userRepository.save(userById);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }



    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepository.delete(user);

    }
    @Override
    public UserDto registerNewUser(UserDto userDto) throws Exception {
       Optional<User> user1 =  userRepository.findByEmail(userDto.getUser_email());
       if(user1.isPresent()){
           throw new Exception("Email already exits...please login");
       }
       else {
           User user = this.modelMapper.map(userDto, User.class);
           // encoded the password
           user.setUser_password(this.passwordEncoder.encode(user.getUser_password()));
           // add roles if needed
           User newUser = this.userRepository.save(user);
           return this.modelMapper.map(newUser, UserDto.class);
       }
    }
}
