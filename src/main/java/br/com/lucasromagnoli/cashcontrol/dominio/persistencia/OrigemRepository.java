package br.com.lucasromagnoli.cashcontrol.dominio.persistencia;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Origem;
import br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common.GenericDAO;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Repository;
import static br.com.lucasromagnoli.cashcontrol.dominio.entidade.QOrigem.origem;
/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Repository
public class OrigemRepository extends GenericDAO<Origem, Long> {
    public boolean existeByNome(Origem origemConsulta) {
        return newQuery()
                .select(Projections.fields(Origem.class, origem.id))
                .from(origem)
                .where(origem.nome.equalsIgnoreCase(origemConsulta.getNome()))
                .fetchFirst() != null;
    }

    public boolean existe(Origem origemConsulta) {
        return newQuery()
                .select(Projections.fields(Origem.class, origem.id))
                .from(origem)
                .where(origem.id.eq(origemConsulta.getId()))
                .fetchFirst() != null;
    }
}
