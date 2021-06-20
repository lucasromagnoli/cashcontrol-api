package br.com.lucasromagnoli.cashcontrol.dominio.persistencia;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Parcelamento;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.GenericDAO;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static br.com.lucasromagnoli.cashcontrol.dominio.entidade.QParcelamento.parcelamento;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Repository
public class ParcelamentoRepository extends GenericDAO<Parcelamento, Long> {
    public boolean existe(Parcelamento parcelamentoConsulta) {
        return Objects.nonNull(newQuery()
                .select(Projections.fields(Parcelamento.class, parcelamento.id))
                .from(parcelamento)
                .where(parcelamento.id.eq(parcelamentoConsulta.getId()))
                .fetchFirst());
    }

    public void remover(Parcelamento parcelamentoRemover) {
        delete(parcelamento)
                .where(parcelamento.id.eq(parcelamentoRemover.getId()))
                .execute();
    }
}
