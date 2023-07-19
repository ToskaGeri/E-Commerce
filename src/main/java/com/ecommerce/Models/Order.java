package com.ecommerce.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq" , sequenceName = "order_id_seq",initialValue = 1, allocationSize = 1)
    @Column(name = "Order_Id")
    private Long orderId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "Cart_Id")
    private Cart cart;

    @OneToMany
    @JsonIgnore
    @JoinTable(
            name = "Order_OrderLine",
            joinColumns = @JoinColumn(name = "Order_Id"),
            inverseJoinColumns = @JoinColumn(name = "Order_Line_ID"))
    private List<OrderLine> orderLines;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "Order_Details_Id")
    private OrderDetails orderDetails;

}
