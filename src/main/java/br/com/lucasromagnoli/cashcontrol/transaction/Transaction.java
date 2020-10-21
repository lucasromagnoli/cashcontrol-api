package br.com.lucasromagnoli.cashcontrol.transaction;

import br.com.lucasromagnoli.cashcontrol.expense.Expense;
import br.com.lucasromagnoli.cashcontrol.income.Income;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"id", "value", "transactionTypeEnum", "date"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaction {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private BigDecimal value;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum transactionTypeEnum;

    @JsonIgnore
    @OneToOne(mappedBy = "transaction", fetch = FetchType.LAZY)
    private Income income;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id")
    private Expense expense;
}
