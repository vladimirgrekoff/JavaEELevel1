package com.grekoff.lesson4.repositories;

import com.grekoff.lesson4.data.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
//@Component
public class ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(List.of(
                new Product(1, 1L, "Хлеб",  40.20f),
                new Product(2, 2L, "Масло", 100.45f),
                new Product(3, 3L, "Сыр",  150.99f),
                new Product(4, 4L, "Мандарины", 130.50f),
                new Product(5, 5L, "Яблоки", 150.20f)
        ));
    }

    public List<Product> getAllProducts() {
        return products;
    }


    public Product findById(Long id) {
        return  products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void addProduct(Long id, String title, Float cost) {
        int newIndex = (products.size() + 1);
        Product productAdded = new Product(newIndex, id, title, cost);
        products.add(productAdded);
    }

    public void addProductWithParameters(Integer index, Long id, String title, Float cost) {
        if(index != null && !title.equals("") && cost > 0f) {
            int idx = getIndex(index);
            Product productAdded = new Product(idx, id, title, cost);
            products.add(idx,productAdded);
            checkIndex(products);
        }
    }

    private int getIndex(Integer index) {
        int idx = 0;
        int maxIdx = products.size() - 1;
        if ((index - 1) <= 0){
            idx = 0;
        } else if((index - 1) > maxIdx) {
            idx = maxIdx + 1;
        } else if ((index - 1) > 0 && (index - 1) <= maxIdx) {
            idx = index - 1;
        }
        return idx;
    }
    private void checkIndex(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getIndex() != (i + 1)){
                products.get(i).setIndex(i + 1);
            }
        }
    }
    public void deleteById(Long id){
        products.removeIf(product -> product.getId().equals(id));
        checkIndex(products);
    }
}
