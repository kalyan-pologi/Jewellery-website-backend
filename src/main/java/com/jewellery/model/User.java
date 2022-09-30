package com.jewellery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="USER")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;


    @NotBlank(message = "user name is required !!")
    @Size(min = 4,max = 20,message = "min 4 and max 20 characters are allowed !!")
    private String user_name;

    @NotNull
    @Column(unique = true)
    @Email(message = "Please enter valid Email address !!")
    private String user_email;

    @NotNull(message = "password is required !!")
    @Size(min = 4,max = 20,message = "min 4 and max 20 characters are allowed !!")
    private String user_password;


}
