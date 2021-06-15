package br.com.lucasromagnoli.cashcontrol.web.v1.mapper;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Movimentacao;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.movimentacao.MovimentacaoCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.movimentacao.MovimentacaoConsultarResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Mapper
public interface MovimentacaoMapper {
    MovimentacaoConsultarResponseDTO entidadeParaResponse(Movimentacao movimentacao);

    @Mapping(target = "origem.id", source = "origemId")
    @Mapping(target = "data", dateFormat = "dd/MM/yyyy")
    @Mapping(target = "categoria.id", source = "categoriaId")
    Movimentacao requestParaEntidade(MovimentacaoCadastrarRequestDTO movimentacaoCadastrarRequestDTO);
}
