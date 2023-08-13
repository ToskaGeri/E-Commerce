package com.ecommerce.Service.Impl;

import com.ecommerce.Errors.CustomError;
import com.ecommerce.Exceptions.ProductNotFoundException;
import com.ecommerce.Models.Product;
import com.ecommerce.Repository.ProductRepository;
import com.ecommerce.Service.ProductService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.ecommerce.Utils.ErrorConstants.*;

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
    public List<Product> findAll(Specification<Product> specification) {
        return productRepository.findAll(specification);
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

    @Override
    public Product updateProduct(Product product, Long id) throws CustomError {

        if(!productRepository.existsById(id))
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND_ERROR_MESSAGE, PRODUCT_NOT_FOUND_ERROR_CODE);

        Product lastProduct = productRepository.getReferenceById(id);

        lastProduct.setProductName(product.getProductName());
        lastProduct.setProductCategory(product.getProductCategory());
        lastProduct.setProductDescription(product.getProductDescription());
        lastProduct.setProductsInStock(product.isProductsInStock());
        lastProduct.setProductPrice(product.getProductPrice());

        productRepository.saveAndFlush(lastProduct);
        return lastProduct;
    }


}
