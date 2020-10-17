package br.com.lucasromagnoli.cashcontrol.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
