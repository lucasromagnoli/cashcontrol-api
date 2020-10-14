package br.com.lucasromagnoli.cashcontrol.subcategory;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class SubcategoryDto {
    private String id;
    private String name;
    private String description;

    @JsonAlias("category_id")
    private String categoryId;
}
