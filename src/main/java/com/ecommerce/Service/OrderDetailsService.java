package com.ecommerce.Service;

import com.ecommerce.Dto.DeliveryOrder;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderDetailsService {

    public List<DeliveryOrder> getAllDeliveryOrders();
}
