package com.ecommerce.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "Orders")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq" , sequenceName = "order_id_seq",initialValue = 1, allocationSize = 1)
    @Column(name = "Order_Id")
    private Long orderId;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Order_OrderLine",
            joinColumns = @JoinColumn(name = "Order_Id"),
            inverseJoinColumns = @JoinColumn(name = "Order_Line_ID"))
    private List<OrderLine> orderLines;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Order_Details_Id")
    private OrderDetails orderDetails;

}
