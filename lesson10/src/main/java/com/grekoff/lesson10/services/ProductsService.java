package com.grekoff.lesson10.services;

import com.grekoff.lesson10.dto.ProductDto;
import com.grekoff.lesson10.entities.Product;
import com.grekoff.lesson10.exceptions.ResourceNotFoundException;
import com.grekoff.lesson10.repositories.ProductsRepository;
import com.grekoff.lesson10.repositories.specifications.ProductsSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer page, Integer size) {
        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }
        return productsRepository.findAll(spec, PageRequest.of(page,size));
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }
    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productsRepository.findById(productDto.getId()).orElseThrow(()-> new ResourceNotFoundException("Продукт отсутствует в списке, id: " + productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(product.getTitle());
        return product;
    }
}