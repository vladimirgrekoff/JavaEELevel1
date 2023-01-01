package com.grekoff.lesson4.repositories;


import com.grekoff.lesson4.data.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository {

    private List<Product> productsInCart;


    @PostConstruct
    public void init() {
        this.productsInCart =  new ArrayList<>();
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public Product findByString(String findStr) {
        Product findProduct = null;
        String productString;
        for (Product p : productsInCart) {
            productString = p.getId() + p.getTitle() + p.getCost();
            if (productString.endsWith(".0")) {
                productString = productString.replace(".0", "");
            }
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

    public void addProduct(Product product) {
        productsInCart.add(product);
    }

    public Product restoreProduct(String findString){
        Product restProduct = findByString(findString);
        return restProduct;
    }

    public void removeProduct(Product productRemoved){
        productsInCart.remove(productRemoved);
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
