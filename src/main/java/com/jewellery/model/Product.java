package com.jewellery.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "product_id")
public class Product {
    @Id
    @Column(name="product_id")
    private int product_id;

    @NotBlank(message = "product name is required !!")
    @Size(min = 4,max = 20,message = "min 4 and max 20 characters are allowed !!")
    @Column(name="product_name")
    private String product_name;

    @NotNull(message = "product desc is required !!")
    @Column(name="product_desc")
    private String product_desc;

//    @NotNull(message = "product image is required !!")
@Column(name="product_image")
    private String product_image;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
//    @JsonBackReference(value = "product-category")
    private Category category;

//    @ManyToMany(cascade = CascadeType.PERSIST,fetch= FetchType.EAGER)
//    @ManyToMany(cascade = CascadeType.PERSIST)
@ManyToMany(mappedBy = "products",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})

//    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @JsonBackReference(value = "product-user")

private Set<User> users ;
//    private Set<User> users;

}
