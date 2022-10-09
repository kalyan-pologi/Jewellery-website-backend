package com.jewellery.Jwt;

import com.jewellery.model.User;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
    private String jwt;
    private String user_name;

}
