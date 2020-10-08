package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.subcategory.Subcategory;
import br.com.lucasromagnoli.cashcontrol.transaction.Transaction;
import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Expense {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal value;

    private LocalDate date;

    @ManyToOne
    private Origin origin;

    private PaymentTypeEnum paymentTypeEnum;

    private FrequencyTypeEnum frequencyTypeEnum;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @OneToOne
    private Installment installment;

    @OneToOne
    private Subscription subscription;

    @OneToMany(mappedBy = "expense")
    private List<Transaction> transactions;
}
