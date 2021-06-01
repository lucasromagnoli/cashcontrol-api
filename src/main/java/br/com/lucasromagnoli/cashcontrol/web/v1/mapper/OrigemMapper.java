package br.com.lucasromagnoli.cashcontrol.web.v1.mapper;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.origem.OrigemCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.origem.OrigemCadastrarResponseDTO;
import org.mapstruct.Mapper;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Mapper
public interface OrigemMapper {
    OrigemCadastrarResponseDTO entidadeParaResponse(Origem origem);
    Origem requestParaEntidade(OrigemCadastrarRequestDTO origemCadastrarRequestDTO);
}
