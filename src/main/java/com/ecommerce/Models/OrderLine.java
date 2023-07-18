package com.ecommerce.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Order_Line")
public class OrderLine {

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
