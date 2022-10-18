package com.jewellery.model;

import lombok.*;;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    private int product_id;

    @NotBlank(message = "product name is required !!")
    @Size(min = 4, max = 20, message = "min 4 and max 20 characters are allowed !!")
    @Column(name = "product_name")
    private String product_name;

    @NotNull(message = "product desc is required !!")
    @Column(name = "product_desc")
    private String product_desc;

    @Column(name = "product_image", length = 1000)
    private byte[] product_image;

    //    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users;

}
