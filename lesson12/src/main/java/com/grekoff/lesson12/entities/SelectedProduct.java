package com.grekoff.lesson12.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedProduct {
        private Long id;
        private String title;
        private int price;
        private int count;

}
