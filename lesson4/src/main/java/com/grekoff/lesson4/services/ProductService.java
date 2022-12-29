package com.grekoff.lesson4.services;

import com.grekoff.lesson4.data.Product;
import com.grekoff.lesson4.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductService {
    private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }


}
