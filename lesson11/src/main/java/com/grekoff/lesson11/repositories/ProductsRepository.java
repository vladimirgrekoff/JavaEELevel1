package com.grekoff.lesson11.repositories;

import com.grekoff.lesson11.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>,  JpaSpecificationExecutor<Product> {

}

