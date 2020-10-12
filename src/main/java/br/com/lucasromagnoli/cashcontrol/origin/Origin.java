package br.com.lucasromagnoli.cashcontrol.origin;

import br.com.lucasromagnoli.cashcontrol.validator.Required;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
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
    @Required(operations = {ValidatorOperation.UPDATE, ValidatorOperation.DELETE})
    private Integer id;

    @Column(name = "name")
    @Required(operations = {ValidatorOperation.CREATE, ValidatorOperation.UPDATE})
    private String name;

}
