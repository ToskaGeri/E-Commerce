package com.ecommerce.Controller.Main;

import com.ecommerce.Dto.ApiResponse;
import com.ecommerce.Dto.DeliveryOrder;
import com.ecommerce.Models.OrderDetails;
import com.ecommerce.Models.OrderStatus;
import com.ecommerce.Service.OrderDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courier/")
public class CourierController {

    private final OrderDetailsService orderDetailsService;

    public CourierController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("orders_to_deliver")
    public ApiResponse<List<OrderDetails>> delivery(){
        return new ApiResponse<>(orderDetailsService.getAllDeliveryOrders(), String.valueOf(HttpStatus.OK));
    }

    @PatchMapping("changeStatusToDelivering/{id}")
    public ApiResponse<OrderDetails> changeStatus(@PathVariable Long id){
        return new ApiResponse<>(orderDetailsService.changeOrderStatusToDelivering(id), String.valueOf(HttpStatus.OK));
    }

    @PatchMapping("changeStatusToDelivered/{id}")
    public ApiResponse<OrderDetails> changeStatusToDelivered(@PathVariable Long id){
        return new ApiResponse<>(orderDetailsService.changeOrderStatusToDelivered(id), String.valueOf(HttpStatus.OK));
    }
}
