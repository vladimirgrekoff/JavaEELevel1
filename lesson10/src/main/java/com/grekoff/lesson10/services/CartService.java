package com.grekoff.lesson10.services;

import com.grekoff.lesson10.controllers.ProductsController;
import com.grekoff.lesson10.dto.SelectedProductDto;
import com.grekoff.lesson10.entities.SelectedProduct;
import com.grekoff.lesson10.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component
@Service
public class CartService {
    private CartRepository cartRepository;
    private ProductsController productsController;


//    public void setProductController(ProductsController productsController) {
//        this.productsController = productsController;
//    }

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
