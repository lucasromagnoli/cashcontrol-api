package br.com.lucasromagnoli.cashcontrol.dominio.persistencia;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Receita;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.GenericDAO;
import org.springframework.stereotype.Repository;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Repository
public class ReceitaRepository extends GenericDAO<Receita, Long> {
}
