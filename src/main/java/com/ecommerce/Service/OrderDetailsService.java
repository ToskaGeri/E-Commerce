package com.ecommerce.Service;

import com.ecommerce.Dto.DeliveryOrder;
import com.ecommerce.Models.OrderDetails;
import com.ecommerce.Models.OrderStatus;

import java.util.List;


public interface OrderDetailsService {

    public List<OrderDetails> getAllDeliveryOrders();

    public OrderDetails changeOrderStatusToDelivering(Long id);

    public OrderDetails changeOrderStatusToDelivered(Long id);
}
