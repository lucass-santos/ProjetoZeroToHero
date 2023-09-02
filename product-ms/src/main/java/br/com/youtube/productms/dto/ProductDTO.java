package br.com.youtube.productms.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;
    private String description;
    private double price;
    private boolean available;
}
