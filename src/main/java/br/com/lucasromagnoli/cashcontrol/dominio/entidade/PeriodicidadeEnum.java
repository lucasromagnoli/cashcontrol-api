package br.com.lucasromagnoli.cashcontrol.dominio.entidade;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
public enum PeriodicidadeEnum {
    QUINZENAL,
    MENSAL,
    ANUAL;

    public static LocalDate incrementar(LocalDate data, PeriodicidadeEnum periodicidade) {
        if (Objects.nonNull(data) && Objects.nonNull(periodicidade)) {
            if (PeriodicidadeEnum.QUINZENAL.equals(periodicidade)) {
                return data.plusDays(15L);
            } else if (PeriodicidadeEnum.MENSAL.equals(periodicidade)) {
                return data.plusMonths(1L);
            } else if (PeriodicidadeEnum.ANUAL.equals(periodicidade)) {
                return data.plusYears(1L);
            }
        }

        return null;
    }
}
