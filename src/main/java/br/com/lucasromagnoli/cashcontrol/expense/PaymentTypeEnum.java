package br.com.lucasromagnoli.cashcontrol.expense;

public enum PaymentTypeEnum {
    CASH_PAYMENT("À Vista"),
    INSTALLMENT_PAYMENT("Parcelamento"),
    SUBSCRIPTION_PAYMENT("Assinatura");

    private final String label;

    PaymentTypeEnum(String label) {
        this.label = label;
    }
}
