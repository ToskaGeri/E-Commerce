package com.ecommerce.Service.Impl;

import com.ecommerce.Models.Product;
import com.ecommerce.Repository.ProductRepository;
import com.ecommerce.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {

        Product newProduct = new Product();
        newProduct.setProductName(product.getProductName());
        newProduct.setProductCategory(product.getProductCategory());
        newProduct.setProductDescription(product.getProductDescription());
        newProduct.setProductsInStock(product.isProductsInStock());
        newProduct.setProductPrice(product.getProductPrice());

        return productRepository.saveAndFlush(newProduct);
    }


    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


}
