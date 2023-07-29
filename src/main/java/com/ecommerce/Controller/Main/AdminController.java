package com.ecommerce.Controller.Main;

import com.ecommerce.Dto.ApiResponse;
import com.ecommerce.Models.Product;
import com.ecommerce.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }
    private boolean checkRoleAdmin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SCOPE_ADMIN"));
    }

    @GetMapping("/")
    public ApiResponse<String> admin(){
        if(checkRoleAdmin()) {
            return new ApiResponse<>("Admin", String.valueOf(HttpStatus.OK));
        }else
            return new ApiResponse<>("Access Denied", 403);
    }


    @PostMapping("/createProduct")
    public ApiResponse<Product> createProduct(@RequestBody Product product){
        if(checkRoleAdmin()) {
            return new ApiResponse<>(productService.createProduct(product), String.valueOf(HttpStatus.CREATED));
        }else
            return new ApiResponse<>("Access Denied", 403);

    }

    @PatchMapping("/updateProduct/{id}")
    public ApiResponse<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
        if(checkRoleAdmin()) {
            return new ApiResponse<>(productService.updateProduct(product,id), String.valueOf(HttpStatus.OK));
        }else
            return new ApiResponse<>("Access Denied", 403);
    }
}
