package com.jewellery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {


    @Id
    private int category_id;

    @NotBlank(message = "category name field is required !!")
    @Size(min = 4,max = 20,message = "min 4 and max 20 characters are allowed !!")
    private String category_name;

    @NotNull(message = "category desc is required !!")
    private String category_desc;

    private byte[] category_image;

}
