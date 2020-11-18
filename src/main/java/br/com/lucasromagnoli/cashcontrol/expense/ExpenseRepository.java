package br.com.lucasromagnoli.cashcontrol.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Repository
interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query(value = "select s.id from Subscription s where s.nextTransaction <= ?1 and s.active = true")
    List<Integer> findAllSubscriptionIdBeforeDate(LocalDate before);

    @Query("select e from Expense e where e.subscription.id in :ids")
    List<Expense> findAllBySubscriptionId(@Param("ids") List<Integer> subscriptionIds);
}
