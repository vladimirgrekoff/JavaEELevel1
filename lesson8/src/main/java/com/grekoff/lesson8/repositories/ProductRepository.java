package com.grekoff.lesson8.repositories;

import com.grekoff.lesson8.entities.Product;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(int minPrice);
    List<Product> findAllByPriceLessThanEqual(int maxPrice);
    List<Product> findAllByPriceBetween(int minPrice, int maxPrice);

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);



}

