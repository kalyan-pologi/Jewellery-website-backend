package com.jewellery.controller;

import com.jewellery.Jwt.JwtAuthRequest;
import com.jewellery.Jwt.JwtAuthResponse;
import com.jewellery.Jwt.JwtTokenHelper;
import com.jewellery.config.*;
import com.jewellery.model.User;
import com.jewellery.model.UserDto;
import com.jewellery.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/auth")
public class AuthController{
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private CustomUserDetailServiceImpl customUserDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@Valid @RequestBody JwtAuthRequest jwtAuthRequest) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuthRequest.getUser_email(),jwtAuthRequest.getUser_password()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("username or password Incorrect");
        }
        catch (InternalAuthenticationServiceException e){
            throw new Exception("Email id not found, please register!!");
        }
        UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtAuthRequest.getUser_email());
        String jwt = jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setJwt(jwt);
        jwtAuthResponse.setUser_name(userDetails.getUsername());

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid  @RequestBody UserDto user) throws Exception {
        UserDto registeredUser = this.userServiceImpl.registerNewUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
