package com.ecommerce.Dto;

import com.ecommerce.Models.OrderStatus;
import lombok.Data;

@Data
public class DeliveryOrder {

    private Long orderId;

    private double orderPrice;

    private OrderStatus orderStatus;
}
