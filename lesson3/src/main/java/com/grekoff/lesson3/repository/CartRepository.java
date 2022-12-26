package com.grekoff.lesson3.repository;

import com.grekoff.lesson3.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CartRepository {

    private List<Product> productsInCart;

    private ProductRepository productRepository;

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setProductsInCart(List<Product> productsInCart) {
        this.productsInCart = productsInCart;
    }


    public List<Product> getProductsInCart() {
        return productsInCart;
    }


    public CartRepository() {
    }


    public CartRepository(List<Product> productsInCart) {
        setProductsInCart(productsInCart);
    }

    @PostConstruct
    public void init() {
        productsInCart = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        productsInCart.add(product);
    }
    public void restoreProduct(String findString){
        Product restProduct = findByString(findString);
        productRepository.addProductWithParameters(restProduct.getId(), restProduct.getTitle(), restProduct.getCost());
        removeProduct(restProduct);
    }
    public void removeProduct(Product productRemoved){
        productsInCart.remove(productRemoved);
    }

    public Product findByString(String findStr) {
        Product findProduct = null;
        String productString;
        for (Product p : productsInCart) {
            productString = p.getId() + p.getTitle() + p.getCost();
            if (productString.equals(findStr)) {
                findProduct = p;
            }
        }
        if (findProduct == null){
            throw new RuntimeException("Продукт отсутствует в списке");
        }else {
            return findProduct;
        }
    }

    public List<Product> getAllProducts() {
        return productsInCart;
    }

    public void clear() {
        productsInCart.clear();
    }

    public void checkCart() {
        if (productsInCart.size() > 0){
            clear();
        }
    }
}