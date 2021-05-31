package br.com.lucasromagnoli.cashcontrol.dominio.entidade;

import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.TransferObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Entity
public class Receita extends TransferObject<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descricao;

    @Override
    public Long getKey() {
        return id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
