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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final OrderService orderService;

    private final ProductService productService;

    private final UserService userService;

    public UserController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    private boolean checkRoleUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SCOPE_USER"));
    }

    @PostMapping("/order")
    public ApiResponse<Order> createOrder(Authentication authentication){
        if(checkRoleUser()){
            UserEntity user = (UserEntity) userService.loadUserByUsername(authentication.getName());
            return new ApiResponse<>(orderService.addOrderToCart(user.getUserId()), String.valueOf(HttpStatus.OK));}
        else
            return new ApiResponse<>("Access Denied", 403);
    }

    @PostMapping("/createOrderLine")
    public ApiResponse<Order> createOrderLine(Authentication authentication, @RequestBody OrderLine orderLine){
        if(checkRoleUser()){
            UserEntity user = (UserEntity) userService.loadUserByUsername(authentication.getName());
            return new ApiResponse<>(orderService.addOrderLineToOrder(orderLine, user.getUserId()), String.valueOf(HttpStatus.CREATED));
        } else
            return new ApiResponse<>("Access Denied", 403);
    }

    @GetMapping("/allOrders")
    public ApiResponse<List<Order>> allOrders(Authentication authentication){
        UserEntity user = (UserEntity) userService.loadUserByUsername(authentication.getName());
        if(checkRoleUser()){
            return new ApiResponse<>(orderService.getAllOrders(user.getCart()), String.valueOf(HttpStatus.OK));
        }else
            return new ApiResponse<>("Access Denied", 403);
    }

    @GetMapping("/products")
    public ApiResponse<List<Product>> getProducts(){
            return new ApiResponse<>(productService.getAllProducts(), String.valueOf(HttpStatus.OK));
    }

}
