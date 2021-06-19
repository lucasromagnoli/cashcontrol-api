package br.com.lucasromagnoli.cashcontrol.dominio.negocio;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Categoria;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Grupo;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.TipoMovimentacaoEnum;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador.CategoriaValidacaoNegocio;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Service
@Transactional(readOnly = true)
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaValidacaoNegocio categoriaValidacaoNegocio;

    public Page<Categoria> listar(Pageable pageable) {
        return categoriaRepository.listar(pageable);
    }

    public Categoria consultarPeloId(Long id) {
        return Optional.ofNullable(categoriaRepository.pesquisaPorId(id)).orElseThrow(() -> new RegistroNaoEncontrado(Categoria.class, "id", id));
    }

    @Transactional(readOnly = false)
    public Categoria salvar(Categoria categoria) {
        categoriaValidacaoNegocio.validarSalvar(categoria);
        categoriaRepository.save(categoria);
        return categoria;
    }

    @Transactional(readOnly = false)
    public Categoria atualizar(Categoria categoria) {
        categoriaValidacaoNegocio.validarAtualizar(categoria);
        categoriaRepository.atualizar(categoria);
        return categoria;
    }

    @Transactional(readOnly = false)
    public void remover(Long id) {
        Categoria categoria = new Categoria(id);
        categoriaValidacaoNegocio.validarRemover(categoria);
        categoriaRepository.remover(categoria);
    }

    public boolean existeComMesmoNome(Categoria categoria) {
        return categoriaRepository.existeByNome(categoria);
    }

    public boolean existeById(Categoria categoria) {
        return categoriaRepository.existe(categoria);
    }

    public boolean existeByGrupo(Grupo grupo) {
        return categoriaRepository.existeByGrupoId(grupo);
    }

    public boolean pertenceAoGrupoComTipoDeMovimentacao(Categoria categoria, TipoMovimentacaoEnum tipoMovimentacao) {
        return categoriaRepository.pertenceAoGrupoComTipoDeMovimentacao(categoria, tipoMovimentacao);
    }
}
