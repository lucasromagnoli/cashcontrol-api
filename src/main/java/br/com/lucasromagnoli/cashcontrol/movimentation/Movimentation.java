package br.com.lucasromagnoli.cashcontrol.movimentation;


import br.com.lucasromagnoli.cashcontrol.expense.Expense;
import br.com.lucasromagnoli.cashcontrol.expense.FrequencyTypeEnum;
import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import br.com.lucasromagnoli.cashcontrol.subcategory.Subcategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "movimentation")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({"id", "value", "description", "date", "type", "frequencyTypeEnum", "expense", "subcategory"})
public class Movimentation {
    
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
        
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "origin_id")
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
    @JsonProperty(value = "frequency")
    private FrequencyTypeEnum frequencyTypeEnum;

    @Column(name = "date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    
    @ManyToOne()
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
}
