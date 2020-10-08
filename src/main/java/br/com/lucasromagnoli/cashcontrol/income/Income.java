package br.com.lucasromagnoli.cashcontrol.income;

import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import br.com.lucasromagnoli.cashcontrol.subcategory.Subcategory;
import br.com.lucasromagnoli.cashcontrol.transaction.Transaction;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Income {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private BigDecimal value;

    @ManyToOne(cascade = {
            CascadeType.PERSIST
    })
    private Origin origin;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @OneToMany(mappedBy = "income", cascade = {
            CascadeType.PERSIST,
            CascadeType.REMOVE
    })
    private List<Transaction> transactions;
}
