package br.com.lucasromagnoli.cashcontrol.category;

import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import br.com.lucasromagnoli.cashcontrol.subcategory.Subcategory;
import br.com.lucasromagnoli.cashcontrol.validator.Required;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Data
@Entity
@Table(name = "category")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({"id, name, description", "type"})
public class Category {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Required(operations = {ValidatorOperation.UPDATE, ValidatorOperation.DELETE})
    private Integer id;

    @Column(name = "name")
    @Required(operations = {ValidatorOperation.CREATE, ValidatorOperation.UPDATE})
    private String name;

    @Column(name = "description")
    private String description;

    @JsonEnumDefaultValue
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @Required(operations = {ValidatorOperation.CREATE})
    private TransactionTypeEnum type;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Subcategory> subcategoryList;

}
