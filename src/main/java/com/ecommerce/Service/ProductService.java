package com.ecommerce.Service;

import com.ecommerce.Models.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public List<Product> findAll(Specification<Product> specification);

    public Product createProduct(Product product);

    public void deleteProductById(Long id);

    public Product updateProduct(Product product,Long id);
}
