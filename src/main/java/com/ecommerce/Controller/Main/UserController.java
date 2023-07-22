package com.ecommerce.Controller.Main;

import com.ecommerce.Dto.ApiResponse;
import com.ecommerce.Models.Order;
import com.ecommerce.Models.OrderLine;
import com.ecommerce.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/user/")
public class UserController {

    public final OrderService orderService;

    public UserController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("{id}/order")
    public ApiResponse<Order> createOrder(@PathVariable Long id){
        return new ApiResponse<>(orderService.addOrderToCart(id), String.valueOf(HttpStatus.OK));
    }

    @PostMapping("createOrderLine/{id}")
    public ApiResponse<Order> createOrderLine(@PathVariable Long id, @RequestBody OrderLine orderLine){
        return new ApiResponse<>(orderService.addOrderLineToOrder(orderLine,id), String.valueOf(HttpStatus.CREATED));
    }

    @GetMapping("allOrders")
    public ApiResponse<List<Order>> allOrders(){
        return new ApiResponse<>(orderService.getAllOrders(), String.valueOf(HttpStatus.OK));
    }

}
