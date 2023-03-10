package com.grekoff.lesson11.controllers;

import com.grekoff.lesson11.converters.ProductConverter;
import com.grekoff.lesson11.dto.ProductDto;
import com.grekoff.lesson11.dto.SelectedProductDto;
import com.grekoff.lesson11.entities.SelectedProduct;
import com.grekoff.lesson11.services.CartService;
import com.grekoff.lesson11.validators.ProductValidator;
import com.grekoff.lesson11.validators.SelectedProductValidator;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;

    private final ProductConverter productConverter;

    private final SelectedProductValidator selectedProductValidator;
    private final ProductValidator productValidator;


    // GET http://localhost:8189/lesson11

    @GetMapping
    public List<SelectedProduct> getAllProductsInCart() {
        return cartService.findAll();
    }

    @GetMapping("/{id}")
    public SelectedProductDto getProductById(@PathVariable Long id) {
        SelectedProduct selectedProduct = cartService.findById(id);
        return productConverter.selectedProductToSelectedProductDto(selectedProduct);
    }

    @PostMapping
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public SelectedProductDto addNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        SelectedProduct selectedProduct = productConverter.dtoToSelectedProduct(productDto);
        SelectedProduct selectedProductInCart = cartService.addProductInCart(selectedProduct);
        return productConverter.selectedProductToSelectedProductDto(selectedProductInCart);
    }

    @PutMapping
    public SelectedProductDto updateProduct(@RequestBody SelectedProductDto selectedProductDto) {
        selectedProductValidator.validate(selectedProductDto);
        SelectedProduct selectedProduct = productConverter.selectedProductDtoToSelectedProduct(selectedProductDto);
        SelectedProduct selectedProductInCart = cartService.updateProductInCart(selectedProduct);
        return productConverter.selectedProductToSelectedProductDto(selectedProductInCart);
    }


    @DeleteMapping ("/{id}")
    public void deleteProductById(@PathVariable Long id){
        cartService.deleteById(id);
    }

    @DeleteMapping
    @PermitAll
    public void deleteAll(){
        cartService.clearCart();
    }

}

