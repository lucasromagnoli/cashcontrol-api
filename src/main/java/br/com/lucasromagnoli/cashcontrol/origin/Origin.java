package br.com.lucasromagnoli.cashcontrol.origin;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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

}
