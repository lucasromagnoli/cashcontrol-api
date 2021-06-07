package br.com.lucasromagnoli.cashcontrol.dominio.negocio;

import br.com.lucasromagnoli.cashcontrol.common.exception.RegistroNaoEncontrado;
import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Grupo;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.validador.GrupoValidacaoNegocio;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.GrupoRepository;
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
public class GrupoService {
    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoValidacaoNegocio grupoValidacaoNegocio;

    public Page<Grupo> listar(Pageable pageable) {
        return grupoRepository.listar(pageable);
    }

    public Grupo consultarPeloId(Long id) {
        return Optional.ofNullable(grupoRepository.pesquisaPorId(id)).orElseThrow(() -> new RegistroNaoEncontrado(Grupo.class, "id", id));
    }

    @Transactional(readOnly = false)
    public Grupo salvar(Grupo grupo) {
        grupoValidacaoNegocio.validarSalvar(grupo);
        grupoRepository.save(grupo);
        return grupo;
    }

    @Transactional(readOnly = false)
    public Grupo atualizar(Grupo grupo) {
        grupoValidacaoNegocio.validarAtualizar(grupo);
        grupoRepository.atualizar(grupo);
        return grupo;
    }

    @Transactional(readOnly = false)
    public void remover(Long id) {
        Grupo grupo = new Grupo(id);
        grupoValidacaoNegocio.validarRemover(grupo);
        grupoRepository.remover(grupo);
    }

    public boolean existeComMesmoNome(Grupo grupo) {
        return grupoRepository.existeByNome(grupo);
    }

    public boolean existeById(Grupo grupo) {
        return grupoRepository.existe(grupo);
    }
}
