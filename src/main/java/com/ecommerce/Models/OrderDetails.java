package com.ecommerce.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Order_Details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_details_id_seq")
    @SequenceGenerator(name = "order_id_seq" , sequenceName = "order_details_id_seq",initialValue = 1, allocationSize = 1)
    @Column(name = "Order_Details_Id")
    private Long orderDetailsId;

    @Column(name = "Order_Price")
    private double orderPrice;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "Order_Id")
    private Order order;

}
