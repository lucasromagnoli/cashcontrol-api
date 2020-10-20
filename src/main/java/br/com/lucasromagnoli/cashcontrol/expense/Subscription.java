package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.validator.Required;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({"amount", "frequencyType", "active", "nextTransaction"})
public class Subscription {

    @Id
    @JsonIgnore
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Required(operations = {ValidatorOperation.DELETE})
    private Integer id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "active")
    private boolean active;

    @Column(name = "next_transaction")
    private LocalDate nextTransaction;

    @Column(name = "frequency")
    @Required(operations = {ValidatorOperation.CREATE})
    private FrequencyTypeEnum frequencyType;

    @JsonIgnore
    @OneToOne(mappedBy = "installment")
    private Expense expense;

}
