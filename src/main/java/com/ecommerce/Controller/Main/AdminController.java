package com.ecommerce.Controller.Main;

import com.ecommerce.Dto.ApiResponse;
import com.ecommerce.Models.Product;
import com.ecommerce.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController("/admin")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ApiResponse<String> admin(){
        return new ApiResponse<>("Admin", String.valueOf(HttpStatus.OK));
    }



    @PostMapping("/createProduct")
    public ApiResponse<Product> createProduct(@RequestBody Product product){
        return new ApiResponse<>(productService.createProduct(product), String.valueOf(HttpStatus.CREATED));
    }

    @PatchMapping("/updateProduct/{id}")
    public ApiResponse<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
        return new ApiResponse<>(productService.updateProduct(product,id), String.valueOf(HttpStatus.OK));
    }
}
