package com.ecommerce.Service.Impl;

import com.ecommerce.Models.*;
import com.ecommerce.Repository.OrderDetailsRepository;
import com.ecommerce.Repository.OrderRepository;
import com.ecommerce.Repository.UserRepository;
import com.ecommerce.Service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailsRepository orderDetailsRepository;

    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.userRepository = userRepository;
    }

    private Order createOrder() {

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderPrice(0);
        orderDetails.setOrderStatus(OrderStatus.Pending);

        Order newOrder = new Order();
        orderDetails.setOrder(newOrder);
        newOrder.setOrderDetails(orderDetails);

        newOrder.setOrderLines(null);
        return newOrder;
    }

    @Override
    public Order addOrderToCart(Long id) {

        UserEntity userEntity = userRepository.getReferenceById(id);
        Cart cart = userEntity.getCart();
        Order order = createOrder();

        List<Order> orderList = cart.getOrderList();
        orderList.add(order);

        cart.setOrderList(orderList);
        order.setCart(cart);

        orderDetailsRepository.saveAndFlush(order.getOrderDetails());
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }
}
