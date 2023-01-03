package com.grekoff.hibernate.h2.repository;

import com.grekoff.hibernate.h2.data.Product;

import java.util.List;

public interface ProductDao {
    Product findById(Long id);

    Product findByTitle(String title);

    List<Product> findAll();

    void deleteById(Long id);

    Product saveOrUpdate(Product product);
}
