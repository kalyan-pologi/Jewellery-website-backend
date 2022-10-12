package com.jewellery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Category {
    @Id
    private int category_id;

    @NotBlank(message = "category name field is required !!")
    @Size(min = 4,max = 20,message = "min 4 and max 20 characters are allowed !!")
    private String category_name;

    @NotNull(message = "category desc is required !!")
    private String category_desc;

//    @NotNull(message = "category image is required !!")
    private String category_image;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
