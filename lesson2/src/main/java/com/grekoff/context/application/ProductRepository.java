package com.grekoff.context.application;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<Product>(Arrays.asList(
                new Product(1L, "хлеб", 40f),
                new Product(2L, "масло", 100f),
                new Product(3L, "сыр", 150f),
                new Product(4L, "мандарины", 130.50f),
                new Product(5L, "яблоки", 150.20f)
        ));
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException());
    }

    public void showAll(){
        System.out.println("Список доступных продуктов:");
        for (Product p: products) {
            System.out.println(p.toString());
        }
        System.out.println();
    }

}
