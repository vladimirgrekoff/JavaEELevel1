package com.grekoff.lesson4.services;

import com.grekoff.lesson4.controllers.ProductController;
import com.grekoff.lesson4.data.Product;
import com.grekoff.lesson4.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EditProductsService {
    private ProductRepository productRepository;

    private ProductController productController;


    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    private EditProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public void deleteById(Long id) {
        Product productRemoved = productRepository.findById(id);
        productRepository.deleteById(id);
    }
    public void addProduct(Integer index, Long id, String title, Float cost) {
        if (index == null) {
            productRepository.addProduct(id, title, cost);
        } else {
            productRepository.addProductWithParameters(index, id, title, cost);
        }
    }

    public void changeScore(Long productId, Integer delta) {
        Product product = productRepository.findById(productId);
        product.setCost(product.getCost() + delta);
    }
}
