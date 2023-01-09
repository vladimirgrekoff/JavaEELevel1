package com.grekoff.lesson7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.grekoff.lesson7.services.ProductService;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    // GET http://localhost:8189/lesson7

    @GetMapping("/")
    public String showMainPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products_page";
    }

    @GetMapping("/edit")
    public String showEditProduct(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products_edit_page";
    }

    @PostMapping("/products/add")
    public String saveNewProduct(Model model, @RequestParam(name = "title") String title, @RequestParam(name = "price") Integer price) {
        productService.saveNewProduct(title, price);
        model.addAttribute("products", productService.findAll());
        return "products_edit_page";
    }

    @GetMapping("/products/remove/{id}")
    public String saveNewProduct(Model model, @PathVariable Long id) {
        productService.deleteById(id);
        model.addAttribute("products", productService.findAll());
        return "products_edit_page";
    }

    @GetMapping ("/products/{id}")
    public String showProductInfo(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.findById(id));
        return "product_info_page";
    }


    @GetMapping("/products_search_page")
    public String showSearchProduct(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products_search_page";
    }


    @GetMapping("/products/find_by_min")
    public String findByMinPrice(Model model, @RequestParam(name = "min") Integer min) {
        model.addAttribute("products", productService.findByMinPrice(min));
        return "products_search_page";
    }

    @GetMapping("/products/find_by_max")
    public String findByMaxPrice(Model model, @RequestParam(name = "max") Integer max) {
        model.addAttribute("products", productService.findByMaxPrice(max));
        return "products_search_page";
    }

    @GetMapping("/products/find_between_price")
    public String findByMaxPrice(Model model, @RequestParam(name = "minPrice") Integer minPrice, @RequestParam(name = "maxPrice") Integer maxPrice) {
        model.addAttribute("products", productService.findAllBetweenPrice(minPrice, maxPrice));
        return "products_search_page";
    }

}