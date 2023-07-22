package com.ecommerce.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq" , sequenceName = "product_id_seq",initialValue = 1, allocationSize = 1)
    @Column(name = "Product_ID")
    private Long productId;

    @Column(name = "Product_Name")
    private String productName;

    @Column(name = "Product_Description")
    private String productDescription;

    @Column(name = "Product_Category")
    private String productCategory;

    @Column(name = "In_Stock")
    private boolean productsInStock;

    @Column(name = "Product_Price")
    private double productPrice;

    //One to one
    //OrderLine

}
