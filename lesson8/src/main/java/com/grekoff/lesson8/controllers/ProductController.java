package com.grekoff.lesson8.controllers;

import com.grekoff.lesson8.entities.Product;
import com.grekoff.lesson8.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    // GET http://localhost:8189/lesson8

    @GetMapping("/products")
    public List<Product> showMainPage() {
        return productService.findAllProduct();
    }

    @GetMapping("/products/pages")
    public Page<Product> getPages(@RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return productService.getPages(offset, limit);
    }


    @GetMapping("/edit")
    public List<Product> showEditPage() {
        return productService.findAllProduct();
    }

    @GetMapping("/search")
    public List<Product> showSearchPage() {
        return productService.findAllProduct();
    }


    @GetMapping("/edit/delete/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }


    @GetMapping("/edit/change_price")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta){
        productService.changePrice(productId, delta);
    }


    @GetMapping("/edit/add_product")
    public void addProduct(@RequestParam String title, @RequestParam Integer price) {
        productService.addProduct(title, price);
    }

    @GetMapping("/search/find_by_min")
    public List<Product> findByMinPrice(@RequestParam(name = "min") Integer min) {
        return productService.findByMinPrice(min);
    }

    @GetMapping("/search/find_by_max")
    public List<Product> findByMaxPrice(@RequestParam(name = "max") Integer max) {
        return productService.findByMaxPrice(max);
    }

    @GetMapping("/search/find_between_price")
    public List<Product> findByBetweenPrice(@RequestParam(name = "minPrice") Integer minPrice, @RequestParam(name = "maxPrice") Integer maxPrice) {
        return productService.findAllBetweenPrice(minPrice, maxPrice);
    }
}
