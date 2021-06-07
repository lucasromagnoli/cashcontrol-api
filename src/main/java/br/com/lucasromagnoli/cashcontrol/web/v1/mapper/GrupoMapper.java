package br.com.lucasromagnoli.cashcontrol.web.v1.mapper;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Grupo;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.grupo.GrupoAtualizarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.grupo.GrupoCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.grupo.GrupoConsultarResponseDTO;
import org.mapstruct.Mapper;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Mapper
public interface GrupoMapper {
    Grupo requestParaEntidade(GrupoCadastrarRequestDTO grupoCadastrarRequestDTO);
    Grupo requestParaEntidade(GrupoAtualizarRequestDTO grupoAtualizarRequestDTO);
    GrupoConsultarResponseDTO entidadeParaResponse(Grupo grupo);
}
