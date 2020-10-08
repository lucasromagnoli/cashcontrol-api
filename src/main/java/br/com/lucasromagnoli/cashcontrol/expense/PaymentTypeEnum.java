package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.javaee.useful.support.validation.EnumParseException;

import java.util.Arrays;

public enum PaymentTypeEnum {
    DEBIT_PAYMENT(new String[] { "Debit", "D", "Debito", "Débito" }),
    INSTALLMENT_PAYMENT(new String[]{ "Parcelamento", "P", "Installment", "I" }),
    SUBSCRIPTION_PAYMENT(new String[]{ "Assinatura", "A", "Subscription", "S" });

    private final String[] labels;

    PaymentTypeEnum(String[] labels) {
        this.labels = labels;
    }

    public static PaymentTypeEnum parse(String typeString) {
        for (PaymentTypeEnum type : values()) {
            if (Arrays.stream(type.labels).anyMatch(t -> t.equalsIgnoreCase(typeString))) {
                return type;
            }
        }

        throw new EnumParseException();
    }
}
