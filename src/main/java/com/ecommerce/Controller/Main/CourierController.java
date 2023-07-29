package com.ecommerce.Controller.Main;

import com.ecommerce.Dto.ApiResponse;
import com.ecommerce.Models.OrderDetails;
import com.ecommerce.Service.OrderDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courier")
public class CourierController {

    private final OrderDetailsService orderDetailsService;

    public CourierController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    private boolean checkRoleCourier(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SCOPE_COURIER"));
    }

    @GetMapping ("/")
    public ApiResponse<String> admin(){
        if(checkRoleCourier())
            return new ApiResponse<>("Courier", String.valueOf(HttpStatus.OK));
        else
            return new ApiResponse<>("Access Denied", 403);
    }

    @GetMapping("/orders_to_deliver")
    public ApiResponse<List<OrderDetails>> delivery(){
        if(checkRoleCourier())
            return new ApiResponse<>(orderDetailsService.getAllDeliveryOrders(), String.valueOf(HttpStatus.OK));
        else
            return new ApiResponse<>("Access Denied", 403);
    }

    @PatchMapping("/changeStatusToDelivering/{id}")
    public ApiResponse<OrderDetails> changeStatus(@PathVariable Long id){
        if(checkRoleCourier())
            return new ApiResponse<>(orderDetailsService.changeOrderStatusToDelivering(id), String.valueOf(HttpStatus.OK));
        else
            return new ApiResponse<>("Access Denied", 403);
    }

    @PatchMapping("/changeStatusToDelivered/{id}")
    public ApiResponse<OrderDetails> changeStatusToDelivered(@PathVariable Long id){
        if(checkRoleCourier())
            return new ApiResponse<>(orderDetailsService.changeOrderStatusToDelivered(id), String.valueOf(HttpStatus.OK));
        else
            return new ApiResponse<>("Access Denied", 403);
    }
}
