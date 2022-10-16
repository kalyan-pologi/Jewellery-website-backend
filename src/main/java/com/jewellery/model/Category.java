package com.jewellery.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="CATEGORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "category_id")
public class Category {
    @Id
    @Column(name="category_id")
    private int category_id;
    @NotBlank(message = "category name field is required !!")
    @Size(min = 4,max = 20,message = "min 4 and max 20 characters are allowed !!")
    @Column(name="category_name")
    private String category_name;

    @NotNull(message = "category desc is required !!")
    @Column(name="category_desc")
    private String category_desc;

//    @NotNull(message = "category image is required !!")
@Column(name="category_image")
    private String category_image;


    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)

//    @JsonManagedReference(value = "product-category")
    private List<Product> products = new ArrayList<>();

}
