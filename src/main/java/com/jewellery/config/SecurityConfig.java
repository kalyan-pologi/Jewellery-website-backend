package com.jewellery.config;

import com.jewellery.Jwt.JwtAuthenticationEntryPoint;
import com.jewellery.Jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String[] PUBLIC_URLS = {
            "/auth/**",
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**"

    };
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .authorizeRequests()
               .antMatchers(PUBLIC_URLS).permitAll()
//               .anyRequest().authenticated()
               .anyRequest().permitAll()
               .and()
               .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

       http
               .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//               .httpBasic();
    }
    /// configure method...
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public UserDetailsService getUserDetailService() {
        return new CustomUserDetailServiceImpl() ;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public FilterRegistrationBean coresFilter(){
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.addAllowedOriginPattern("*");
//        corsConfiguration.addAllowedHeader("Authorization");
//        corsConfiguration.addAllowedHeader("Content-Type");
//        corsConfiguration.addAllowedHeader("Accept");
//        corsConfiguration.addAllowedMethod(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH").toString());
//        corsConfiguration.setMaxAge(3600L);
////        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000","https://you.server.domain.com"));
////        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
////        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("http://localhost:3000"));
////        corsConfiguration.addAllowedOrigin("*");
////        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "responseType", "Authorization"));
////        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
//
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(-110);
//        return bean;
//
//    }

//    public WebMvcConfigurer configure(){
//       return new WebMvcConfigurer() {
//           @Override
//           public void addCorsMappings(CorsRegistry registry) {
//               registry
//                       .addMapping("/**")
////                       .allowedOrigins("http://localhost:3000")
//                       .allowedMethods("*")
//                       .maxAge(3600L)
//                       .allowedHeaders("*")
//                       .exposedHeaders("Authorization")
//                       .allowCredentials(true)
//                       .allowedOrigins("*");
//           }
//       };
//    }
    
}
