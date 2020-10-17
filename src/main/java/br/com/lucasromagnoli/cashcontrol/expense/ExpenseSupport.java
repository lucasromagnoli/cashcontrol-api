package br.com.lucasromagnoli.cashcontrol.expense;

public class ExpenseSupport {
    private ExpenseSupport() {}

    public static boolean isInstallment(Expense expense) {
        return PaymentTypeEnum.INSTALLMENT_PAYMENT.equals(expense.getPaymentType());
    }

    public static boolean isSubscription(Expense expense) {
        return PaymentTypeEnum.SUBSCRIPTION_PAYMENT.equals(expense.getPaymentType());
    }

    public static boolean isDebit(Expense expense) {
        return PaymentTypeEnum.DEBIT_PAYMENT.equals(expense.getPaymentType());
    }
}
