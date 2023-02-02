package com.grekoff.lesson11.repositories;


import com.grekoff.lesson11.entities.SelectedProduct;
import com.grekoff.lesson11.exceptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository {

    private static List<SelectedProduct> productsInCart;



    @PostConstruct
    private static void init() {
        productsInCart =  new ArrayList<>();
    }

    public List<SelectedProduct> findAll() {
        return productsInCart;
    }



    public SelectedProduct findById(Long id) {
        return productsInCart.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Продукт отсутствует в списке, id: " + id));
    }

    public boolean isPresentInCart(Long id) {
        boolean result = false;
        for (SelectedProduct p : productsInCart) {
            if(p.getId() == id) {
                result = true;
            }
        }
        return result;
    }
    public SelectedProduct addProduct(SelectedProduct selectedProduct) {
        if (isPresentInCart(selectedProduct.getId())){
            return update(selectedProduct);
        } else {
            productsInCart.add(selectedProduct);
            return productsInCart.get(productsInCart.indexOf(selectedProduct));
        }
    }

    @Transactional
    public SelectedProduct update(SelectedProduct selectedProduct) {
        SelectedProduct selectedProductInCart = findById(selectedProduct.getId());
        selectedProductInCart.setPrice(selectedProduct.getPrice());
        selectedProductInCart.setTitle(selectedProduct.getTitle());
        selectedProductInCart.setCount(selectedProductInCart.getCount() + 1);
        return selectedProductInCart;
    }

    public void deleteProduct(Long id){
        SelectedProduct selectedProduct = productsInCart.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Продукт отсутствует в списке, id: " + id));
        if (selectedProduct.getCount() > 1) {
            selectedProduct.setCount(selectedProduct.getCount() - 1);
        } else {
            productsInCart.remove(selectedProduct);
        }
    }

    public void clear() {
        productsInCart.clear();
    }

}
