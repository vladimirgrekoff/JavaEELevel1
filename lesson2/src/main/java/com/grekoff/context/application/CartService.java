package com.grekoff.context.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class CartService {
    private ProductService productService;
    private Cart cart;


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductService getProductService() {
        return productService;
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    @PostConstruct
    public void init(){
        setProductService(productService);
        List<Product> productsInCart = new ArrayList<>();
        setCart(new Cart(productsInCart));

    }

    public void intro(){
        System.out.println("Добавление в корзину: № из списка доступных продуктов;\n" +
                "Удаление из корзины: -№ из списка в корзине;\n" +
                "Очистка корзины: 0; Выход: 00.\n");
    }

    public void showProducts() {
        productService.showProductsInRepository();
    }

    public void addToCart(Long id) {
        Product product = productService.findById(id);
        cart.addProduct(product);
        showProducts();
        cart.showAll();
    }

    public void removeFromCart(Long id){
        cart.removeProduct(id);
        showProducts();
        cart.showAll();
    }

    public void clearCart() {
        showProducts();
        cart.clear();
    }

    public boolean checkInput(String cmd){
        boolean result = true;
        if (cmd.equals("00")) {
            result  = false;
        } else {
            long numId = Long.parseLong(cmd);
            if (numId > 0) {
                addToCart(numId);
                result  = true;
            } else if (numId < 0) {
                removeFromCart(numId);
                result  = true;
            } else if (numId == 0) {
                clearCart();
                result  = true;
            }
        }
        return result;
    }
}
