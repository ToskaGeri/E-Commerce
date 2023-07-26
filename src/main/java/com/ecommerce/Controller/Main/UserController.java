package com.ecommerce.Controller.Main;

import com.ecommerce.Dto.ApiResponse;
import com.ecommerce.Models.Order;
import com.ecommerce.Models.OrderLine;
import com.ecommerce.Models.Product;
import com.ecommerce.Models.UserEntity;
import com.ecommerce.Service.Impl.UserService;
import com.ecommerce.Service.OrderService;
import com.ecommerce.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/user/")
public class UserController {

    private final OrderService orderService;

    private final ProductService productService;

    private final UserService userService;

    public UserController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }


    @PostMapping("order")
    public ApiResponse<Order> createOrder(Authentication authentication){
        UserEntity user = (UserEntity) userService.loadUserByUsername(authentication.getName());
        return new ApiResponse<>(orderService.addOrderToCart(user.getUserId()), String.valueOf(HttpStatus.OK));
    }

    @PostMapping("createOrderLine")
    public ApiResponse<Order> createOrderLine(Authentication authentication, @RequestBody OrderLine orderLine){
        UserEntity user = (UserEntity) userService.loadUserByUsername(authentication.getName());
        return new ApiResponse<>(orderService.addOrderLineToOrder(orderLine, user.getUserId()), String.valueOf(HttpStatus.CREATED));
    }

    @GetMapping("allOrders")
    public ApiResponse<List<Order>> allOrders(){
        return new ApiResponse<>(orderService.getAllOrders(), String.valueOf(HttpStatus.OK));
    }

    @GetMapping("products")
    public ApiResponse<List<Product>> getProducts(){
        return new ApiResponse<>(productService.getAllProducts(), String.valueOf(HttpStatus.OK));
    }

}
