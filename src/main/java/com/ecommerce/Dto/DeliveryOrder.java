package com.ecommerce.Dto;

import com.ecommerce.Models.Order;
import com.ecommerce.Models.OrderStatus;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DeliveryOrder {

    private Order order;

    private double orderPrice;

    private OrderStatus orderStatus;
}
