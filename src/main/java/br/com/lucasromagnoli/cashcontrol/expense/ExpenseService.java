package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.transaction.TransactionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseBusinessValidator expenseBusinessValidator;

    @Transactional(readOnly = false)
    public Expense save(Expense expense) {
        expenseBusinessValidator.validateSave(expense);
        expense.setTransactions(TransactionSupport.generateTransactions(expense));

        if (!ExpenseSupport.isInstallment(expense)) {
            expense.setInstallment(null);
            expense.getSubscription().setAmount(expense.getValue());
        } else if (!ExpenseSupport.isSubscription(expense)) {
            expense.setSubscription(null);
            expense.getInstallment().setAmount(BigDecimal.valueOf(
                    expense.getValue().floatValue() * expense.getInstallment().getQuantity()));
        }

        if (ExpenseSupport.isSubscription(expense)) {
            expense.getSubscription().setActive(true); // TODO: 10/16/20 - Validar se é o melhor local
            expense.getSubscription().setNextTransaction(expense.getDate()
                    .plusMonths(expense.getSubscription().getFrequencyType().getMonths()));
        }

        return expenseRepository.save(expense);
    }

    @Transactional(readOnly = false)
    public Expense update(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Transactional(readOnly = true)
    public List<Expense> findAllSubscriptionsActiveAndReadyToInsertNextTransaction() {
        List<Integer> ids = expenseRepository.findAllSubscriptionIdBeforeDate(
                LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));

        List<Expense> expenses = new ArrayList<>();
        if (!ids.isEmpty()) {
            expenses.addAll(expenseRepository.findAllBySubscriptionId(ids));
        }

        return expenses;
    }

    @Transactional(readOnly = true)
    public boolean existsWithId(Integer id) {
        return expenseRepository.existsById(id);
    }
}
