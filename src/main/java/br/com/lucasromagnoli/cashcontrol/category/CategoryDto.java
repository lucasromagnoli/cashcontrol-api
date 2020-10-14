package br.com.lucasromagnoli.cashcontrol.category;

import lombok.Data;

@Data
public class CategoryDto {
    private String id;
    private String name;
    private String description;
    private String type;
}
