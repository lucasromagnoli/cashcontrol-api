package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.validator.Required;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.xml.validation.Validator;
import java.math.BigDecimal;


@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subscription {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Required(operations = {ValidatorOperation.DELETE})
    private Integer id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "frequency")
    @Required(operations = {ValidatorOperation.CREATE})
    private FrequencyTypeEnum frequencyType;

    @OneToOne(mappedBy = "installment")
    private Expense expense;

}
