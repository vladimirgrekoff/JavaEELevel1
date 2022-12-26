package com.grekoff.context.application;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cart {
    private List<Product> productsInCart;


    public void setProductsInCart(List<Product> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public Cart() {
    }

    public Cart(List<Product> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public void addProduct(Product product) {
        long size = (productsInCart.size() + 1);
        Long newId = size;
        Product productAdded = new Product(newId, product.getTitle(), product.getCost());
        productsInCart.add(productAdded);
    }

    public void removeProduct(Long id){
        Product productRemoved = findById(id);
        productsInCart.remove(productRemoved);
        for (int i = 0; i < productsInCart.size(); i++) {
            if (productsInCart.get(i).getId() != (long) (i + 1)){
                productsInCart.get(i).setId((long) (i + 1));
            }
        }
    }
    private Long checkSignId(Long id){
        if (id < 0) {
            id = id * -1;
        }
        return id;
    }
    public Product findById(Long id) {
        Long checkedId = checkSignId(id);
        return productsInCart.stream().filter(p -> p.getId().equals(checkedId)).findFirst().orElseThrow(()->new RuntimeException());
    }

    public void showAll(){
        System.out.println("Список продуктов в корзине:");
        for (Product p: productsInCart) {
            System.out.println(p.toString());
        }
        System.out.println();
    }

    public void clear() {
        productsInCart.clear();
        showAll();
    }
}
