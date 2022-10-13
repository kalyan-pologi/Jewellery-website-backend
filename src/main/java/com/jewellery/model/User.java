package com.jewellery.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "user_id")
public class User {

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


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

//    @JsonManagedReference(value = "product-user")
    private List<Product> products = new ArrayList<>();

}
