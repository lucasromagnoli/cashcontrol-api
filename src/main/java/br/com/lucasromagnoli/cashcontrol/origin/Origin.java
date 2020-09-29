package br.com.lucasromagnoli.cashcontrol.origin;

import br.com.lucasromagnoli.cashcontrol.movimentation.Movimentation;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "origin")
    private List<Movimentation> listMovimentation;
}
