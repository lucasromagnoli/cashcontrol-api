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
public class Parcelamento extends TransferObject<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private BigDecimal valorParcela;

    @Column(nullable = false)
    private Byte quantidadeParcelas;

    @Column(nullable = false)
    private LocalDate dataPrimeiraParcela;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PeriodicidadeEnum periodicidade;

    @ManyToOne(optional = false)
    private Origem origem;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "parcelamento")
    private List<Movimentacao> movimentacoes;

    public Parcelamento() {
    }

    public Parcelamento(Long id) {
        this.id = id;
    }

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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Byte getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Byte quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public LocalDate getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(LocalDate dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
    }

    public PeriodicidadeEnum getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(PeriodicidadeEnum periodicidade) {
        this.periodicidade = periodicidade;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
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
}
