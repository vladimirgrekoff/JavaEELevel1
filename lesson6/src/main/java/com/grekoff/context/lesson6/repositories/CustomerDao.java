package com.grekoff.context.lesson6.repositories;

import com.grekoff.context.lesson6.entities.Customer;
import com.grekoff.context.lesson6.entities.Product;

import java.util.List;

public interface CustomerDao {
    Customer findById(Long id);
    Customer findByName(String name);
    List<Customer> findAll();
    Customer saveOrUpdate(Customer customer);
    void deleteById(Long id);
    List<Product> findProductsByCustomerId(Long id);
}
