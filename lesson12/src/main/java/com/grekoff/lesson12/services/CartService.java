package com.grekoff.lesson12.services;

import com.grekoff.lesson12.controllers.ProductsController;
import com.grekoff.lesson12.entities.SelectedProduct;
import com.grekoff.lesson12.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component
@Service
public class CartService {
    private CartRepository cartRepository;
    private ProductsController productsController;



    public SelectedProduct findById(Long id) {
        return cartRepository.findById(id);
    }

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<SelectedProduct> findAll() {
        return cartRepository.findAll();
    }

    public void deleteById(Long id){
        cartRepository.deleteProduct(id);
    }

    public SelectedProduct addProductInCart(SelectedProduct selectedProduct) {
        return cartRepository.addProduct(selectedProduct);
    }

    public SelectedProduct updateProductInCart(SelectedProduct selectedProduct) {
        return cartRepository.update(selectedProduct);
    }

    public void clearCart() {
        cartRepository.clear();
    }
}
