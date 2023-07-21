package com.ecommerce.Service;

import com.ecommerce.Models.Cart;
import com.ecommerce.Models.Order;
import com.ecommerce.Models.UserEntity;

import java.util.List;

public interface OrderService {

    public Order addOrderToCart(Long id);

    public List<Order> getAllOrders();
}
