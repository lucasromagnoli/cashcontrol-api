package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import br.com.lucasromagnoli.cashcontrol.subcategory.Subcategory;
import br.com.lucasromagnoli.cashcontrol.transaction.Transaction;
import br.com.lucasromagnoli.cashcontrol.validator.Required;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({"id",
        "description",
        "value",
        "date",
        "paymentType",
        "subcategory",
        "origin",
        "installment",
        "subscription",
        "transactions"})
public class Expense {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Required(operations = {ValidatorOperation.DELETE})
    private Integer id;

    @Required(operations = {ValidatorOperation.CREATE})
    private BigDecimal value;

    @Required(operations = {ValidatorOperation.CREATE})
    private LocalDate date;

    private String description;

    @ManyToOne
    @Required(operations = {ValidatorOperation.CREATE})
    private Origin origin;

    @Required(operations = {ValidatorOperation.CREATE})
    private PaymentTypeEnum paymentType;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    @Required(operations = {ValidatorOperation.CREATE})
    private Subcategory subcategory;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "installment_id")
    private Installment installment;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @OneToMany(mappedBy = "expense", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Transaction> transactions;
}
