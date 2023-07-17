package com.ecommerce.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cart_id_seq")
    @SequenceGenerator(name = "cart_id_seq" , sequenceName = "cart_id_seq",initialValue = 1, allocationSize = 1)
    @Column(name = "Cart_ID")
    private Long id;

}
