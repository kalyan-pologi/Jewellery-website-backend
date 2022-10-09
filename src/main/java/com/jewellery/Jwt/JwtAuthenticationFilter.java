package com.jewellery.Jwt;

import com.jewellery.config.CustomUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private CustomUserDetailServiceImpl customUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader =  request.getHeader("Authorization");
        String jwt = null;
        String username = null;
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            jwt = authorizationHeader.substring(7);
            username = jwtTokenHelper.getUsernameFromToken(jwt);
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);
            if (jwtTokenHelper.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}

//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
//            jwt = authorizationHeader.substring(7);
//            try {
//                username = this.jwtTokenHelper.getUsernameFromToken(jwt);
//            } catch (IllegalArgumentException e) {
//                System.out.println("Unable to get Jwt token");
//            } catch (ExpiredJwtException e) {
//                System.out.println("Jwt token has expired");
//            } catch (MalformedJwtException e) {
//                System.out.println("invalid jwt");
//            }
//        } else {
//            System.out.println("Jwt token does not begin with Bearer");
//        }

//   if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//           UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);
//           if (this.jwtTokenHelper.validateToken(jwt, userDetails)) {
//           UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//           userDetails, null, userDetails.getAuthorities());
//           usernamePasswordAuthenticationToken
//           .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//           SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//           } else {
//           System.out.println("Invalid jwt token");
//           }
//     } else {
//       System.out.println("username is null or context is not null");
//     }