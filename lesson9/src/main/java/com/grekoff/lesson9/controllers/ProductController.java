package com.grekoff.lesson9.controllers;

import com.grekoff.lesson9.dto.ProductDto;
import com.grekoff.lesson9.entities.Product;
import com.grekoff.lesson9.exceptions.ResourceNotFoundException;
import com.grekoff.lesson9.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // GET http://localhost:8189/lesson9

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "part_title", required = false) String partTitle,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset,
            @RequestParam(name = "limit", defaultValue = "30") Integer limit
    ) {
        if (offset < 0) {
            offset = 0;
        }
        return productService.find(minPrice, maxPrice, partTitle, offset, limit).map(p -> new ProductDto(p));
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Продукт отсутствует в списке, id: " + id));
    }

    @PostMapping
    public ProductDto addNewProduct(@RequestBody Product product) {
        product.setId(null);
        return new ProductDto(productService.save(product));
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody Product product) {
        return new ProductDto(productService.save(product));
    }


    @DeleteMapping ("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

}
