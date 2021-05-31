package br.com.lucasromagnoli.cashcontrol.web.dto.request.origem;

import br.com.lucasromagnoli.cashcontrol.web.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@JsonIgnoreProperties("id")
public class OrigemCadastrarRequestDTO extends BaseDTO {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
