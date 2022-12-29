package com.grekoff.lesson4.controllers;

import com.grekoff.lesson4.data.Product;
import com.grekoff.lesson4.services.EditProductsService;
import com.grekoff.lesson4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductController {

    private ProductService productService;
    @Autowired
    private EditProductsService editProductsService;


    public ProductService getProductService() {
        return productService;
    }





    public ProductController(ProductService productService, EditProductsService editProductsService) {
        this.productService = productService;
        this.editProductsService = editProductsService;
        editProductsService.setProductController(this);
    }


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/edit_products")
    public List<Product> showEditPage() {
        return editProductsService.getAllProducts();
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




}
