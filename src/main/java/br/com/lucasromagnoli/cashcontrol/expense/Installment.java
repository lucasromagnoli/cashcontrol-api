package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.validator.Required;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({"quantity", "amount"})
public class Installment {

    @Id
    @JsonIgnore
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Required(operations = {ValidatorOperation.DELETE})
    private Integer id;

    @Column(name = "quantity")
    @Required(operations = {ValidatorOperation.CREATE})
    private Integer quantity;

    @Column(name = "amount")
    private BigDecimal amount;

    @JsonIgnore
    @OneToOne(mappedBy = "installment")
    private Expense expense;
}
