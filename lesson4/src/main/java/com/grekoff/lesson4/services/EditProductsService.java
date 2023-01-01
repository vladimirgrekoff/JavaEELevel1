package com.grekoff.lesson4.services;

import com.grekoff.lesson4.controllers.ProductController;
import com.grekoff.lesson4.data.Product;
import com.grekoff.lesson4.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component
@Service
public class EditProductsService {
    private ProductRepository productRepository;
    private CartService cartService;
    private ProductController productController;


    public void setProductController(ProductController productController) {
        this.productController = productController;
        this.cartService = productController.getCartService();
    }


    private EditProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public void deleteById(Long id) {
        Product productRemoved = productRepository.findById(id);
        cartService.addProductInCart(productRemoved);
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
