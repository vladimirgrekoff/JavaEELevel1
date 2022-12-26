package com.grekoff.lesson3.controllers;

import com.grekoff.lesson3.repository.CartRepository;
import org.springframework.ui.Model;
import com.grekoff.lesson3.model.Product;
import com.grekoff.lesson3.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;


    public ProductController(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }


    @GetMapping("/all")
    public String showProductsPage(Model model) {
        model.addAttribute("products", productRepository.getAllProducts());
        cartRepository.checkCart();
        return "products_page";
    }

    @GetMapping("/{id}")
    public String showProductPage(Model model, @PathVariable Long id) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "products_info_page";
    }

    @GetMapping("/edit")
    public String showEditProductsPage(Model model) {
        model.addAttribute("products", productRepository.getAllProducts());
        return "products_edit_page";
    }

    @GetMapping("/remove/{id}")
    public String showEditProductsPage(Model model, @PathVariable Long id) {
        productRepository.removeProduct(id);
        model.addAttribute("products", productRepository.getAllProducts());
        return "products_edit_page";
    }

    @GetMapping("/restore")
    public String showCartPage(Model model) {
        model.addAttribute("productsInCart", cartRepository.getAllProducts());
        return "products_cart_page";
    }

    @GetMapping("/restore/{findString}")
    public String showCartProductsPage(Model model, @PathVariable String findString) {
        cartRepository.restoreProduct(findString);
        model.addAttribute("productsInCart", cartRepository.getAllProducts());
        return "products_cart_page";
    }

    @GetMapping("/add")
    public String showEditProductsPage(Model model, @RequestParam(name = "id") Long id, @RequestParam(name = "title") String title, @RequestParam(name = "cost") Float cost) {
        productRepository.addProductWithParameters(id, title, cost);
        model.addAttribute("products", productRepository.getAllProducts());
        return "products_edit_page";
    }
}
