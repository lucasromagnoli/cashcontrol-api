package br.com.lucasromagnoli.cashcontrol.subcategory;

import br.com.lucasromagnoli.cashcontrol.category.Category;
import br.com.lucasromagnoli.cashcontrol.validator.Required;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "subcategory")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subcategory {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Required(operations = {ValidatorOperation.UPDATE, ValidatorOperation.DELETE})
    private Integer id;
    
    @Column(name = "name", nullable = false)
    @Required(operations = {ValidatorOperation.CREATE, ValidatorOperation.UPDATE})
    private String name;
    
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Required(operations = {ValidatorOperation.CREATE, ValidatorOperation.UPDATE})
    private Category category;
    
}
