package com.grekoff.lesson12.services;

import com.grekoff.lesson12.dto.ProductDto;
import com.grekoff.lesson12.entities.Product;
import com.grekoff.lesson12.exceptions.ResourceNotFoundException;
import com.grekoff.lesson12.repositories.ProductsRepository;
import com.grekoff.lesson12.repositories.specifications.ProductsSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {

    public int page = 0;
    private final ProductsRepository productsRepository;

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer offset, Integer size) {

        checkFirstNumberPage(offset);

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
//        return productsRepository.findAll(spec, PageRequest.of(page,size));

        Page<Product> pageRepository = productsRepository.findAll(spec, PageRequest.of(page,size));

        if (pageRepository.stream().count() > 0) {
            return pageRepository;
        } else {
            page = page - 1;
            return productsRepository.findAll(spec, PageRequest.of(page,size));
        }
    }

    private void checkFirstNumberPage(Integer offset) {
        if (offset != 0) {
            page = page + offset;
            if (page < 0) {
                page = 0;
            }
        } else {
            page = 0;
        }
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