package com.grekoff.lesson10.controllers;

import com.grekoff.lesson10.converters.ProductConverter;
import com.grekoff.lesson10.dto.ProductDto;
import com.grekoff.lesson10.entities.Product;
import com.grekoff.lesson10.exceptions.ResourceNotFoundException;
import com.grekoff.lesson10.services.ProductsService;
import com.grekoff.lesson10.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;

    private final ProductConverter productConverter;

    private final ProductValidator productValidator;


    // GET http://localhost:8189/lesson10

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
        return productsService.findAll(minPrice, maxPrice, partTitle, offset, limit).map(p -> productConverter.entityToDto(p));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productsService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Продукт отсутствует в списке, id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto addNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productsService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productsService.update(productDto);
        return productConverter.entityToDto(product);
    }


    @DeleteMapping ("/{id}")
    public void deleteById(@PathVariable Long id){
        productsService.deleteById(id);
    }

}
