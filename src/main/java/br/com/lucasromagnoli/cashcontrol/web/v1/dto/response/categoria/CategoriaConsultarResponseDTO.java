package br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.categoria;

import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.grupo.GrupoConsultarResponseDTO;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class CategoriaConsultarResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private GrupoConsultarResponseDTO grupo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public GrupoConsultarResponseDTO getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoConsultarResponseDTO grupo) {
        this.grupo = grupo;
    }
}
