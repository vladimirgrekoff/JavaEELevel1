package com.grekoff.lesson11.validators;

import com.grekoff.lesson11.dto.SelectedProductDto;
import com.grekoff.lesson11.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class SelectedProductValidator {
    public void validate(SelectedProductDto selectedProductDto) {
        List<String> errors = new ArrayList<>();

        if (selectedProductDto.getPrice() < 1) {
            errors.add("Цена не может быть меньше 1");
        }

        if (selectedProductDto.getTitle().isBlank()) {
            errors.add("Продукт не может иметь пустое название");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}