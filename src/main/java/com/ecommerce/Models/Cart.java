package com.ecommerce.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cart_id_seq")
    @SequenceGenerator(name = "cart_id_seq" , sequenceName = "cart_id_seq",initialValue = 1, allocationSize = 1)
    @Column(name = "Cart_ID")
    private Long id;

    @OneToMany
    @JsonIgnore
    @JoinTable(
            name = "Cart_Order",
            joinColumns = @JoinColumn(name = "Cart_ID"),
            inverseJoinColumns = @JoinColumn(name = "Order_ID"))
    private List<Order> orderList;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "ID")
    private UserEntity user;

}
