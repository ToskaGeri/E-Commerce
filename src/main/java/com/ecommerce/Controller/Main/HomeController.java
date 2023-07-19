package com.ecommerce.Controller.Main;

import com.ecommerce.Dto.ApiResponse;
import com.ecommerce.Models.Product;
import com.ecommerce.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("createProduct")
    public ApiResponse<Product> createProduct(@RequestBody Product product){
        return new ApiResponse<>(productService.createProduct(product), String.valueOf(HttpStatus.CREATED));
    }
}
