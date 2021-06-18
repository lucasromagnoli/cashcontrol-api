package br.com.lucasromagnoli.cashcontrol.web.v1.mapper;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Movimentacao;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.movimentacao.MovimentacaoAtualizarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.movimentacao.MovimentacaoCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.movimentacao.MovimentacaoConsultarResponseDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.DATE_FORMAT;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Mapper
public abstract class MovimentacaoMapper {
    public abstract MovimentacaoConsultarResponseDTO entidadeParaResponse(Movimentacao movimentacao);

    @Mapping(target = "origem.id", source = "origemId")
    @Mapping(target = "data", dateFormat = DATE_FORMAT)
    @Mapping(target = "categoria.id", source = "categoriaId")
    public abstract Movimentacao requestParaEntidade(MovimentacaoCadastrarRequestDTO movimentacaoCadastrarRequestDTO);

    @Mapping(target = "origem.id", source = "origemId")
    @Mapping(target = "data", dateFormat = DATE_FORMAT)
    @Mapping(target = "categoria.id", source = "categoriaId")
    public abstract Movimentacao requestParaEntidade(MovimentacaoAtualizarRequestDTO movimentacaoAtualizarRequestDTO);

    @AfterMapping
    public void doAfterMapping(MovimentacaoAtualizarRequestDTO source, @MappingTarget Movimentacao target) {
        if (source.getOrigemId() == null) {
            target.setOrigem(null);
        }

        if (source.getCategoriaId() == null) {
            target.setCategoria(null);
        }
    }
}
