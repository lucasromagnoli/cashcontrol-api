package br.com.lucasromagnoli.cashcontrol.web.v1.mapper;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Assinatura;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Parcelamento;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.AssinaturaCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.ParcelamentoCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.ParcelamentoConsultarResponseDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.DATE_FORMAT;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Mapper
public abstract class DespesaMapper {
    public abstract Assinatura requestParaEntidade(AssinaturaCadastrarRequestDTO assinaturaCadastrarRequestDTO);

    public abstract AssinaturaCadastrarRequestDTO entidadeParaResponse(Assinatura parcelamento);

    @Mapping(target = "origem.id", source = "origemId")
    @Mapping(target = "dataPrimeiraParcela", dateFormat = DATE_FORMAT)
    @Mapping(target = "categoria.id", source = "categoriaId")
    public abstract Parcelamento requestParaEntidade(ParcelamentoCadastrarRequestDTO parcelamentoCadastrarRequestDTO);

    @Mapping(target = "dataPrimeiraParcela", dateFormat = DATE_FORMAT)
    public abstract ParcelamentoConsultarResponseDTO entidadeParaResponse(Parcelamento parcelamento);

    @AfterMapping
    public void doAfterMapping(ParcelamentoCadastrarRequestDTO source, @MappingTarget Parcelamento target) {
        if (source.getOrigemId() == null) {
            target.setOrigem(null);
        }

        if (source.getCategoriaId() == null) {
            target.setCategoria(null);
        }
    }
}
