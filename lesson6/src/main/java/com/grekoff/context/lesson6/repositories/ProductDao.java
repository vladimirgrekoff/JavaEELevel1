package com.grekoff.context.lesson6.repositories;

import com.grekoff.context.lesson6.entities.Customer;
import com.grekoff.context.lesson6.entities.Product;

import java.util.List;

public interface ProductDao {
    Product findById(Long id);
    Product findByTitle(String title);
    List<Product> findAll();
    Product saveOrUpdate(Product product);
    void deleteById(Long id);
    List<Customer> findCustomerByProductId(Long id);
}
