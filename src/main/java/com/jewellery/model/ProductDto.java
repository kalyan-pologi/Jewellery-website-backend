package com.jewellery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @Id
    private int product_id;

    @NotBlank(message = "product name is required !!")
    @Size(min = 4,max = 20,message = "min 4 and max 20 characters are allowed !!")
    private String product_name;

    @NotNull(message = "product desc is required !!")
    private String product_desc;

    //    @NotNull(message = "product image is required !!")
    private String product_image;

    private CategoryDto category;

    private List<UserDto> user;
}
