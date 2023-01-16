package com.grekoff.lesson10.dto;

import com.grekoff.lesson10.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    private String title;

    private int price;
}
