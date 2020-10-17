package br.com.lucasromagnoli.cashcontrol.support;

import br.com.lucasromagnoli.cashcontrol.income.Income;
import br.com.lucasromagnoli.cashcontrol.transaction.Transaction;
import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;

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
}
