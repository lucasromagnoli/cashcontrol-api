package br.com.lucasromagnoli.cashcontrol.expense;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class ExpenseDto {
    private String id;
    private String value;
    private String date;
    @JsonAlias("origin_id")
    private String originId;
    @JsonAlias("subcategory_id")
    private String subcategoryId;
    @JsonAlias("payment")
    private String paymentType;
    @JsonAlias("frequency")
    private String frequencyType;
    private String quantity;
    private String amount;

    private String description;

}
