package br.com.lucasromagnoli.cashcontrol.origin;

import br.com.lucasromagnoli.cashcontrol.movimentation.Movimentation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "origin")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Origin {
    
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(fetch = FetchType.EAGER)
    private List<Movimentation> listMovimentation;
}
