package com.jewellery.Jwt;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthRequest {
    private String user_email;
    private String user_password;
}
