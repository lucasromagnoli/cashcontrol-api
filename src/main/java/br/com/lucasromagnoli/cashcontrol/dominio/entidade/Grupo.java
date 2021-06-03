package br.com.lucasromagnoli.cashcontrol.dominio.entidade;

import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.TransferObject;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Entity
public class Grupo extends TransferObject<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private TipoMovimentacaoEnum tipoMovimentacao;

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

    public TipoMovimentacaoEnum getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacaoEnum tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }
}
