package com.grekoff.lesson4.controllers;

import com.grekoff.lesson4.data.Product;
import com.grekoff.lesson4.services.CartService;
import com.grekoff.lesson4.services.EditProductsService;
import com.grekoff.lesson4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private EditProductsService editProductsService;

    @Autowired
    private CartService cartService;


    public ProductService getProductService() {
        return productService;
    }

    public CartService getCartService() {
        return cartService;
    }

    public EditProductsService getEditProductsService() {
        return editProductsService;
    }

    public ProductController(ProductService productService, EditProductsService editProductsService, CartService cartService) {
        this.productService = productService;
        this.editProductsService = editProductsService;
        this.cartService = cartService;
        editProductsService.setProductController(this);
        cartService.setProductController(this);
    }


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        cartService.checkCart();
        return productService.getAllProducts();
    }

    @GetMapping("/edit_products")
    public List<Product> showEditPage() {
        return editProductsService.getAllProducts();
    }

    @GetMapping("/restore")
    public List<Product> showCartPage() {
        return cartService.getProductsInCart();
    }

    @GetMapping("/edit_products/delete/{id}")
    public void deleteById(@PathVariable Long id){
        editProductsService.deleteById(id);
    }


    @GetMapping("/edit_products/change_score")
    public void changeScore(@RequestParam Long productId, @RequestParam Integer delta){
        editProductsService.changeScore(productId, delta);
    }

    @GetMapping("/edit_products/add_product")
    public void addProduct(@RequestParam(required = false) Integer index, @RequestParam Long id, @RequestParam String title, @RequestParam Float cost) {
        editProductsService.addProduct(index, id, title, cost);
        showEditPage();
    }

    @GetMapping("/restore/{findString}")
    public void showCartProductsPage(@PathVariable String findString) {
        cartService.restoreProduct(findString);
        showCartPage();
    }


}
