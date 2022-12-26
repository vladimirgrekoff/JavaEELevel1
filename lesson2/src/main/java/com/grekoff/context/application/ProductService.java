package com.grekoff.context.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void showProductsInRepository(){
        productRepository.showAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }
}
