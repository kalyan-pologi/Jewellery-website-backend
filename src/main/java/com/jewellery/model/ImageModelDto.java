//package com.jewellery.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//
//public class ImageModelDto {
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "type")
//    private String type;
//
//    //image bytes can have large lengths so we specify a value
//    //which is more than the default length for picByte column
//    @Column(name = "picByte", length = 1000)
//    private byte[] picByte;
//
////    private CategoryDto category;
////
////    private ProductDto product;
//
//}
