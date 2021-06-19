package br.com.lucasromagnoli.cashcontrol.dominio.persistencia;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Parcelamento;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.GenericDAO;
import org.springframework.stereotype.Repository;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Repository
public class ParcelamentoRepository extends GenericDAO<Parcelamento, Long> {
}
