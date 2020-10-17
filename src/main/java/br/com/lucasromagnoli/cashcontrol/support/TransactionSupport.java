package br.com.lucasromagnoli.cashcontrol.support;

import br.com.lucasromagnoli.cashcontrol.expense.Expense;
import br.com.lucasromagnoli.cashcontrol.expense.PaymentTypeEnum;
import br.com.lucasromagnoli.cashcontrol.income.Income;
import br.com.lucasromagnoli.cashcontrol.transaction.Transaction;
import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class TransactionSupport {
    private TransactionSupport() {}

    public static Transaction generateTransaction(Income income) {
        Transaction transaction = new Transaction();
        transaction.setDate(income.getDate());
        transaction.setValue(income.getValue());
        transaction.setTransactionTypeEnum(TransactionTypeEnum.INCOME);
        transaction.setIncome(income);

        return transaction;
    }

    public static List<Transaction> generateTransaction(Expense expense) {
        List<Transaction> transactions = new ArrayList<>();
        Integer qtyTransactions = PaymentTypeEnum.INSTALLMENT_PAYMENT.equals(expense.getPaymentType())
                ? expense.getInstallment().getQuantity() : 1;

        for (int index = 0; index < qtyTransactions; index++) {
            Transaction transaction = new Transaction();
            transaction.setDate(expense.getDate().plusMonths(index));
            transaction.setValue(expense.getValue());
            transaction.setTransactionTypeEnum(TransactionTypeEnum.EXPENSE);
            transaction.setExpense(expense);
            transactions.add(transaction);
        }

        return transactions;
    }
}
