package com.ecommerce.Controller.Main;

import com.ecommerce.Dto.ApiResponse;
import com.ecommerce.Models.Order;
import com.ecommerce.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("/user/")
public class UserController {

    public final OrderService orderService;

    public UserController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("{id}/order")
    public ApiResponse<Order> getOrder(@PathVariable Long id){
        return new ApiResponse<>(orderService.addOrderToCart(id), String.valueOf(HttpStatus.OK));
    }


}
