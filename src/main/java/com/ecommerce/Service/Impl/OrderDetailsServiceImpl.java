package com.ecommerce.Service.Impl;

import com.ecommerce.Dto.DeliveryOrder;
import com.ecommerce.Mapper.OrderDetailsMapper;
import com.ecommerce.Models.OrderDetails;
import com.ecommerce.Repository.OrderDetailsRepository;
import com.ecommerce.Service.OrderDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsMapper orderDetailsMapper;

    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsServiceImpl(OrderDetailsMapper orderDetailsMapper, OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsMapper = orderDetailsMapper;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    private DeliveryOrder convertEntityToDto(OrderDetails orderDetails){
        return orderDetailsMapper.toDto(orderDetails);
    }

    @Override
    public List<DeliveryOrder> getAllDeliveryOrders(){
        return orderDetailsRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .toList();
    }
}
