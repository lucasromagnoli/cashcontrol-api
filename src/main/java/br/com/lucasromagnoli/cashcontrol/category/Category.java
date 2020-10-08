package br.com.lucasromagnoli.cashcontrol.category;

import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import br.com.lucasromagnoli.cashcontrol.subcategory.Subcategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "category")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum type;
    
    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Subcategory> subcategoryList;

}
