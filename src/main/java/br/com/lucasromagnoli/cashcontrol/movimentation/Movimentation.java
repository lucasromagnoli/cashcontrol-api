package br.com.lucasromagnoli.cashcontrol.movimentation;


import br.com.lucasromagnoli.cashcontrol.expense.Expense;
import br.com.lucasromagnoli.cashcontrol.expense.FrequencyTypeEnum;
import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "movimentation")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Movimentation {
    
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne()
    private Origin origin;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MovimentationTypeEnum type;
    
    @Column(name = "value")
    private BigDecimal value;
    
    @Column(name = "description")
    private String description;
    
    @Embedded
    private Expense expense;

    @Column(name = "frequency")
    @Enumerated(EnumType.STRING)
    private FrequencyTypeEnum frequencyTypeEnum;

    @Column(name = "date")
    private LocalDate date;
}
