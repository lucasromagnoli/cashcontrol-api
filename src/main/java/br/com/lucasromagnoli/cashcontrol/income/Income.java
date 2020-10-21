package br.com.lucasromagnoli.cashcontrol.income;

import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import br.com.lucasromagnoli.cashcontrol.subcategory.Subcategory;
import br.com.lucasromagnoli.cashcontrol.transaction.Transaction;
import br.com.lucasromagnoli.cashcontrol.validator.Required;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Income {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Required(operations = {ValidatorOperation.UPDATE, ValidatorOperation.DELETE})
    private Integer id;

    @Required(operations = {ValidatorOperation.UPDATE, ValidatorOperation.CREATE})
    private LocalDate date;

    @Required(operations = {ValidatorOperation.UPDATE, ValidatorOperation.CREATE})
    private BigDecimal value;

    private String description;

    @ManyToOne
    @Required(operations = {ValidatorOperation.UPDATE, ValidatorOperation.CREATE})
    private Origin origin;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    @Required(operations = {ValidatorOperation.UPDATE, ValidatorOperation.CREATE})
    private Subcategory subcategory;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.REMOVE
    })
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
