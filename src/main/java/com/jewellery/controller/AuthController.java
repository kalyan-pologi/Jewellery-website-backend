package com.jewellery.controller;

import com.jewellery.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController{
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private CustomUserDetailServiceImpl customUserDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("incorrect username or password", e);
        }
        UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtAuthRequest.getUsername()); ;
        String jwt = jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setJwt(jwt);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
