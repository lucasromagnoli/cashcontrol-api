package br.com.lucasromagnoli.cashcontrol.dominio.negocio;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Receita;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.ReceitaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Service
@Transactional(readOnly = true)
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;

    private static final Logger log = LoggerFactory.getLogger(ReceitaService.class);

    public List<Receita> listar() {
        log.info("Listando todas as receitas");

        return receitaRepository.getAll();
    }

    @Transactional(readOnly = false)
    public Receita cadastrar(Receita receita) {
        log.info("Salvando a receita: {}", receita.getDescricao());
        receitaRepository.save(receita);

        return receita;
    }
}
