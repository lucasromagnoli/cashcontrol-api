package br.com.lucasromagnoli.cashcontrol.dominio.entidade;

import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.TransferObject;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Entity
public class Assinatura extends TransferObject<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PeriodicidadeEnum periodicidade;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private LocalDate dataProximaMovimentacao;

    private BigDecimal valorTotal;

    @Column(nullable = false)
    private BigDecimal valor;

    private Boolean ativo;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @ManyToOne(optional = false)
    private Origem origem;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "assinatura")
    private List<Movimentacao> movimentacoes;

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

    public PeriodicidadeEnum getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(PeriodicidadeEnum periodicidade) {
        this.periodicidade = periodicidade;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public LocalDate getDataProximaMovimentacao() {
        return dataProximaMovimentacao;
    }

    public void setDataProximaMovimentacao(LocalDate dataProximaMovimentacao) {
        this.dataProximaMovimentacao = dataProximaMovimentacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
