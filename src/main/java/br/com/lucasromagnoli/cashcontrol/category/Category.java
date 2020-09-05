package br.com.lucasromagnoli.cashcontrol.category;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
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
    private CategoryType type;

}
