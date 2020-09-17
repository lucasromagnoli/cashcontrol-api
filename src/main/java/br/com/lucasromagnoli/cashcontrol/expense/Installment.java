package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.movimentation.Movimentation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "installment")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Installment {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "amount")
    private BigDecimal amount;
    
    @Column(name = "start")
    private LocalDate start;
    
    @OneToMany(mappedBy = "expense.installment")
    private List<Movimentation> movimentations;
}
