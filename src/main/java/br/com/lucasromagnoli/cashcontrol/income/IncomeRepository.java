package br.com.lucasromagnoli.cashcontrol.income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

}
