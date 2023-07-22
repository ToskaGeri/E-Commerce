package com.ecommerce.Service.Impl;

import com.ecommerce.Dto.DeliveryOrder;
import com.ecommerce.Errors.CustomError;
import com.ecommerce.Exceptions.OrderDoesNotExist;
import com.ecommerce.Mapper.OrderDetailsMapper;
import com.ecommerce.Models.Order;
import com.ecommerce.Models.OrderDetails;
import com.ecommerce.Models.OrderStatus;
import com.ecommerce.Repository.OrderDetailsRepository;
import com.ecommerce.Repository.OrderRepository;
import com.ecommerce.Service.OrderDetailsService;
import org.springframework.stereotype.Service;
import static com.ecommerce.Utils.ErrorConstants.*;
import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsMapper orderDetailsMapper;

    private final OrderDetailsRepository orderDetailsRepository;

    private final OrderRepository orderRepository;

    public OrderDetailsServiceImpl(OrderDetailsMapper orderDetailsMapper, OrderDetailsRepository orderDetailsRepository, OrderRepository orderRepository) {
        this.orderDetailsMapper = orderDetailsMapper;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderRepository = orderRepository;
    }

    private DeliveryOrder convertEntityToDto(OrderDetails orderDetails){
        return orderDetailsMapper.toDto(orderDetails);
    }

    @Override
    public List<OrderDetails> getAllDeliveryOrders(){

        return orderDetailsRepository.findAll();
//                .stream()
//                .map(this::convertEntityToDto)
//                .collect(Collectors.toList());
    }

    @Override
    public OrderDetails changeOrderStatusToDelivering(Long id) throws CustomError {

        if(!orderRepository.existsById(id)){
            throw  new OrderDoesNotExist(ORDER_DOES_NOT_EXIST_MESSAGE, ORDER_DOES_NOT_EXIST_CODE);
        }
        Order order = orderRepository.getReferenceById(id);

        OrderDetails newOrderDetails = order.getOrderDetails();

        newOrderDetails.setOrderStatus(OrderStatus.Delivering);
        orderDetailsRepository.saveAndFlush(newOrderDetails);
        return newOrderDetails;
    }

    @Override
    public OrderDetails changeOrderStatusToDelivered(Long id) throws CustomError {

        if(!orderRepository.existsById(id)){
            throw  new OrderDoesNotExist(ORDER_DOES_NOT_EXIST_MESSAGE, ORDER_DOES_NOT_EXIST_CODE);
        }
        Order order = orderRepository.getReferenceById(id);

        OrderDetails newOrderDetails = order.getOrderDetails();

        newOrderDetails.setOrderStatus(OrderStatus.Delivered);
        orderDetailsRepository.saveAndFlush(newOrderDetails);
        return newOrderDetails;
    }
}
