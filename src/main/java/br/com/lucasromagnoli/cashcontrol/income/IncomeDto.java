package br.com.lucasromagnoli.cashcontrol.income;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Data
public class IncomeDto {
    private String id;
    private String date;
    private String value;
    private String description;
    @JsonAlias("origin_id")
    private String originId;
    @JsonAlias("subcategory_id")
    private String subcategoryId;
}
