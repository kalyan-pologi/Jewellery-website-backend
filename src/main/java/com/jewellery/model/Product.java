package com.jewellery.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    private int product_id;

    @NotBlank(message = "product name is required !!")
    @Size(min = 4,max = 20,message = "min 4 and max 20 characters are allowed !!")
    private String product_name;

    @NotNull(message = "product desc is required !!")
    private String product_desc;

    @NotNull(message = "product image is required !!")
    private String product_image;

    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category category;

}
