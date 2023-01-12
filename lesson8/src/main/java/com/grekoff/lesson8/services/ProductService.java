package com.grekoff.lesson8.services;

import com.grekoff.lesson8.controllers.ProductController;
import com.grekoff.lesson8.entities.Product;
import com.grekoff.lesson8.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private ProductController productController;

    public Page<Product> getPages(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public void addProduct(String title, int price) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        if (product.getPrice() <= 0) {
            return;
        }
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByMinPrice(int minPrice) {
        return productRepository.findAllByPriceGreaterThanEqual(minPrice);
    }

    public List<Product> findByMaxPrice(int maxPrice) {
        return productRepository.findAllByPriceLessThanEqual(maxPrice);
    }

    public List<Product> findAllBetweenPrice(int minPrice, int maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    public void changePrice(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).get();
        product.setPrice(product.getPrice() + delta);
        productRepository.save(product);
    }
}