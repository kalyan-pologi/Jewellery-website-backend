//package com.jewellery.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "image_table")
////@Getter
////@Setter
////@NoArgsConstructor
////@AllArgsConstructor
//public class ImageModel {
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    //image bytes can have large lengths so we specify a value
//    //which is more than the default length for picByte column
//    @Column(name = "picByte", length = 1000)
//    private byte[] picByte;
//
//    @OneToOne(mappedBy = "product_image",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @OneToOne(mappedBy = "category_image",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    private Category category;
//
//
//
//    public ImageModel() {
//        super();
//    }
//
//    public ImageModel(byte[] picByte) {
//
//        this.picByte = picByte;
//    }
//
//    public ImageModel(int id,  byte[] picByte, Product product, Category category) {
//        this.id = id;
//
//        this.picByte = picByte;
//        this.product = product;
//        this.category = category;
//    }
//
//
//
//    public byte[] getPicByte() {
//        return picByte;
//    }
//
//    public void setPicByte(byte[] picByte) {
//        this.picByte = picByte;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//}