package com.jewellery.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "user_id")
public class User {

    @Id
    @Column(name="user_id")
    private int user_id;


    @Size(min = 4,max = 20,message = "min 4 and max 20 characters are allowed !!")
    @NotBlank(message = "user name is required !!")
    @Column(name="user_name")
    private String user_name;


    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Please enter valid Email address !!", regexp =  "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Column(unique = true, name="user_email")
    private String user_email;

    @NotEmpty(message = "password is required !!")
    @Size(min = 4,max = 250,message = "min 4 and max 10 characters are allowed !!")
    @Column(name = "user_password")
    private String user_password;

//    @ManyToMany(mappedBy = "users",cascade = CascadeType.PERSIST)
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST,fetch= FetchType.EAGER)
//    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //    @JoinColumn(name="product_id")
//    @JsonManagedReference(value = "product-user")
    @JoinTable(name = "product_users",
            joinColumns = { @JoinColumn(name = "user_id" , referencedColumnName = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id",referencedColumnName = "product_id") })
    private Set<Product> products ;
//    private List<Product> products = new ArrayList<>();


}
