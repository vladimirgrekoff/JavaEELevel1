package com.grekoff.lesson3.repository;

import com.grekoff.lesson3.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> products;
    private CartRepository cartRepository;

    public CartRepository getCartRepository() {
        return cartRepository;
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @PostConstruct
    public void init() {
        products = new ArrayList<Product>(List.of(
                new Product(1L, "Хлеб", 40f),
                new Product(2L, "Масло", 100f),
                new Product(3L, "Сыр", 150f),
                new Product(4L, "Мандарины", 130.50f),
                new Product(5L, "Яблоки", 150.20f)
        ));

        cartRepository.setProductRepository(this);
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("Продукт отсутствует в списке"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public List<Product> addProduct(Product product) {
        long size = (products.size() + 1);
        Long newId = size;
        Product productAdded = new Product(newId, product.getTitle(), product.getCost());
        products.add(productAdded);
        checkId(products);
        return products;
    }
    public void addProductWithParameters(Long id, String title, Float cost) {
        if(id != null && !title.equals("") && cost > 0f) {
            int idx = getIndex(id);
            Product productAdded = new Product(id, title, cost);
            products.add(idx,productAdded);
            checkId(products);
        }
    }

    private int getIndex(Long id) {
        long longId = id;
        int intId = (int) longId;
        int idx = 0;
        int maxIdx = products.size() - 1;
        if ((intId - 1) <= 0){
            idx = 0;
        } else if((intId - 1) > maxIdx) {
            idx = maxIdx + 1;
        } else if ((intId - 1) > 0 && (intId - 1) <= maxIdx) {
            idx = intId - 1;
        }
        return idx;
    }

    public void removeProduct(Long id){
        Product productRemoved = findById(id);
        cartRepository.addProduct(productRemoved);
        products.remove(productRemoved);
        checkId(products);
    }

    private void checkId(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() != (long) (i + 1)){
                products.get(i).setId((long) (i + 1));
            }
        }
    }
}
