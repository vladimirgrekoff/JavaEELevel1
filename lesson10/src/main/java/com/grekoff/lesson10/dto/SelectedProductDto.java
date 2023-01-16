package com.grekoff.lesson10.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedProductDto {
    private Long id;
    private String title;
    private int price;
    private int count;


}
