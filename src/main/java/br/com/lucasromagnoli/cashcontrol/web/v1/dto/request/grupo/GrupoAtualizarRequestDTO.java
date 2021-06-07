package br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.grupo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public class GrupoAtualizarRequestDTO extends GrupoCadastrarRequestDTO {
    @NotNull
    @Min(1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
