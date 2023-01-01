package com.grekoff.lesson4.services;

import com.grekoff.lesson4.controllers.ProductController;
import com.grekoff.lesson4.data.Product;
import com.grekoff.lesson4.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component
@Service
public class CartService {
    private CartRepository cartRepository;
    private EditProductsService editProductsService;
    private ProductController productController;


    public void setProductController(ProductController productController) {
        this.productController = productController;
        this.editProductsService = productController.getEditProductsService();
    }


    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Product> getProductsInCart() {
        return cartRepository.getProductsInCart();
    }

    public void restoreProduct(String findString){
        Product restProduct = cartRepository.restoreProduct(findString);
        editProductsService.addProduct(restProduct.getIndex(),restProduct.getId(), restProduct.getTitle(), restProduct.getCost());
        cartRepository.removeProduct(restProduct);
    }

    public void addProductInCart(Product product) {
        cartRepository.addProduct(product);
    }

    public void checkCart() {
        cartRepository.checkCart();
    }
}
