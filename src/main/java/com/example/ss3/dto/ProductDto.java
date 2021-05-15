package com.example.ss3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private Integer categoryid;
    private String name;
    private Integer price;
    private Integer quantity;
    private boolean isCheck;
}
