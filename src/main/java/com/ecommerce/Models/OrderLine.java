package com.ecommerce.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Order_Line")
public class OrderLine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_line_id_seq")
    @SequenceGenerator(name = "order_line_id_seq" , sequenceName = "order_line_id_seq",initialValue = 1, allocationSize = 1)
    @Column(name = "Order_Line_ID")
    private Long orderLineId;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "Prdouct_ID")
    private Product product;

}
