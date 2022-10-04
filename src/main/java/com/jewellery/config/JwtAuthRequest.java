package com.jewellery.config;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthRequest {
    private String username;
    private String password;
}
