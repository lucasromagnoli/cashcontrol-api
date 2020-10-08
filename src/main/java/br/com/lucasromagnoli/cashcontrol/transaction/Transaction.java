package br.com.lucasromagnoli.cashcontrol.transaction;

import br.com.lucasromagnoli.cashcontrol.expense.Expense;
import br.com.lucasromagnoli.cashcontrol.income.Income;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
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

    @OneToOne
    @JoinColumn(name = "income_id")
    private Income income;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;
}
