package br.com.lucasromagnoli.cashcontrol.web.v1.mapper;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Categoria;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.categoria.CategoriaAtualizarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.request.categoria.CategoriaCadastrarRequestDTO;
import br.com.lucasromagnoli.cashcontrol.web.v1.dto.response.categoria.CategoriaConsultarResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Mapper
public interface CategoriaMapper {
    @Mapping(target = "grupo.id", source = "grupoId")
    Categoria requestParaEntidade(CategoriaCadastrarRequestDTO categoriaCadastrarRequestDTO);

    @Mapping(target = "grupo.id", source = "grupoId")
    Categoria requestParaEntidade(CategoriaAtualizarRequestDTO categoriaCadastrarRequestDTO);

    CategoriaConsultarResponseDTO entidadeParaResponse(Categoria categoria);
}
