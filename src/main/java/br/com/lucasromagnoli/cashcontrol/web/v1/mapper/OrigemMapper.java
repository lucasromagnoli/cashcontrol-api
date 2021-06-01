package br.com.lucasromagnoli.cashcontrol.web.v1.mapper;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.origem.OrigemCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.origem.OrigemConsultarResponseDTO;
import org.mapstruct.Mapper;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Mapper
public interface OrigemMapper {
    OrigemConsultarResponseDTO entidadeParaResponse(Origem origem);
    Origem requestParaEntidade(OrigemCadastrarRequestDTO origemCadastrarRequestDTO);
}
