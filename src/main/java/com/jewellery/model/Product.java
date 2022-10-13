package com.jewellery.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "product_id")
public class Product {
    @Id
    private int product_id;

    @NotBlank(message = "product name is required !!")
    @Size(min = 4,max = 20,message = "min 4 and max 20 characters are allowed !!")
    private String product_name;

    @NotNull(message = "product desc is required !!")
    private String product_desc;

//    @NotNull(message = "product image is required !!")
    private String product_image;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
//    @JsonBackReference(value = "product-category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonBackReference(value = "product-user")
    private User user;

}
