package com.jewellery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Id
    private int user_id;


    @Size(min = 4,max = 20,message = "min 4 and max 20 characters are allowed !!")
    @NotBlank(message = "user name is required !!")
    private String user_name;


    @Column(unique = true)
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Please enter valid Email address !!", regexp =  "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String user_email;

    @NotEmpty(message = "password is required !!")
    @Size(min = 4,max = 250,message = "min 4 and max 10 characters are allowed !!")
    private String user_password;
}
