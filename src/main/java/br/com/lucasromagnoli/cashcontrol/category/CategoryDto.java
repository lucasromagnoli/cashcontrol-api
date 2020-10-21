package br.com.lucasromagnoli.cashcontrol.category;

import lombok.Data;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Data
public class CategoryDto {
    private String id;
    private String name;
    private String description;
    private String type;
}
