package br.com.lucasromagnoli.cashcontrol.web.dto.request;

import br.com.lucasromagnoli.cashcontrol.web.dto.BaseDTO;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class OrigemRequestDTO extends BaseDTO {
    private Long id;
    private String nome;

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
}
