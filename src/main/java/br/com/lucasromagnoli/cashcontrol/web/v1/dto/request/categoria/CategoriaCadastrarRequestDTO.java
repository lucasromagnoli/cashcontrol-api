package br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.categoria;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.PADRAO_DESCRICAO_MAX_LENGTH;
import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.PADRAO_NOME_MAX_LENGTH;
import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.PADRAO_NOME_MIN_LENGTH;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class CategoriaCadastrarRequestDTO {
    @NotEmpty
    @Size(min = PADRAO_NOME_MIN_LENGTH, max = PADRAO_NOME_MAX_LENGTH)
    private String nome;
    @Size(max = PADRAO_DESCRICAO_MAX_LENGTH)
    private String descricao;

    private Long grupoId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }
}
