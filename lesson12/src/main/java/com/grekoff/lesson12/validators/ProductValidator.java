package com.grekoff.lesson12.validators;

import com.grekoff.lesson12.dto.ProductDto;
import com.grekoff.lesson12.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();

        if (productDto.getPrice() < 1) {
            errors.add("Цена не может быть меньше 1");
        }

        if (productDto.getTitle().isBlank()) {
            errors.add("Продукт не может иметь пустое название");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}

