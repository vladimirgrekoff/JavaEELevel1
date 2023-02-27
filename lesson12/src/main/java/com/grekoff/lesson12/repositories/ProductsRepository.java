package com.grekoff.lesson12.repositories;

import com.grekoff.lesson12.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>,  JpaSpecificationExecutor<Product> {

}

