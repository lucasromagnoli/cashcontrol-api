package br.com.lucasromagnoli.cashcontrol.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    boolean existsByIncomeId(Integer id);

    Transaction findByIncomeId(Integer id);
}
