package com.ecommerce.Service.Impl;

import com.ecommerce.Errors.CustomError;
import com.ecommerce.Exceptions.OrderDoesNotExist;
import com.ecommerce.Exceptions.ProductNotFoundException;
import com.ecommerce.Exceptions.UserNotFoundException;
import com.ecommerce.Models.*;
import com.ecommerce.Repository.OrderDetailsRepository;
import com.ecommerce.Repository.OrderRepository;
import com.ecommerce.Repository.ProductRepository;
import com.ecommerce.Repository.UserRepository;
import com.ecommerce.Service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ecommerce.Utils.ErrorConstants.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailsRepository orderDetailsRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productRepository = productRepository;
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
    public Order addOrderToCart(Long id) throws CustomError {

        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(USER_NOT_FOUND_ERROR_MESSAGE, USER_NOT_FOUND_ERROR_CODE);
        }

        UserEntity userEntity = userRepository.getReferenceById(id);
        Cart cart = userEntity.getCart();
        Order order = createOrder();

        List<Order> orderList = cart.getOrderList();
        orderList.add(order);

        cart.setOrderList(orderList);
        order.setCart(cart);

        //orderDetailsRepository.saveAndFlush(order.getOrderDetails());
        return orderRepository.saveAndFlush(order);
    }

    private OrderLine createOrderLine(OrderLine orderLine) throws CustomError{

        OrderLine newOrderLine = new OrderLine();
        newOrderLine.setQuantity(orderLine.getQuantity());

        if(!productRepository.existsById(orderLine.getProduct().getProductId())){
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND_ERROR_MESSAGE, PRODUCT_NOT_FOUND_ERROR_CODE);
        }

        Product product = productRepository.getReferenceById(orderLine.getProduct().getProductId());
        newOrderLine.setProduct(product);

        return newOrderLine;
    }

    @Override
    public Order addOrderLineToOrder(OrderLine orderLine, Long id) {

        if(!orderRepository.existsById(id)){
            throw new OrderDoesNotExist(ORDER_DOES_NOT_EXIST_MESSAGE, ORDER_DOES_NOT_EXIST_CODE);
        }

        Order order = orderRepository.getReferenceById(id);

        List<OrderLine> orderLines = order.getOrderLines();
        OrderLine orderLine1 = createOrderLine(orderLine);

        orderLines.add(orderLine1);
        order.setOrderLines(orderLines);

        OrderDetails orderDetails = order.getOrderDetails();
        double total = 0.0;

        for(OrderLine line : orderLines){
            total += line.getProduct().getProductPrice() * line.getQuantity();
        }

        orderDetails.setOrderPrice(total);

        orderDetailsRepository.saveAndFlush(orderDetails);
        orderRepository.saveAndFlush(order);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
