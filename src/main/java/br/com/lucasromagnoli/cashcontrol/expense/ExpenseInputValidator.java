package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.exception.CashControlRuntimeException;
import br.com.lucasromagnoli.cashcontrol.validator.CheckRequired;
import br.com.lucasromagnoli.cashcontrol.validator.PredicatesValidator;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorOperation;
import br.com.lucasromagnoli.cashcontrol.validator.ValidatorSupport;

import java.math.BigDecimal;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
public class ExpenseInputValidator {
    private ExpenseInputValidator() {
    }

    public static void validateSave(Expense expense) {
        validateCommonsSave(expense);
        if (PaymentTypeEnum.INSTALLMENT_PAYMENT.equals(expense.getPaymentType())) {
            validateInstallment(expense.getInstallment());
        } else if (PaymentTypeEnum.SUBSCRIPTION_PAYMENT.equals(expense.getPaymentType())) {
            validateSubscription(expense.getSubscription());
        }
    }

    public static void validateDelete(Expense expense) {
        ValidatorSupport.requiredFields(expense, ValidatorOperation.DELETE);
    }

    private static void validateInstallment(Installment installment) {
        try {
            ValidatorSupport.fieldType(Integer.class)
                    .target(installment)
                    .operation(ValidatorOperation.CREATE)
                    .checkRequired(CheckRequired.SINGLE)
                    .field("quantity")
                    .predicate(PredicatesValidator.integerMinValue(2))
                    .message("Quantidade precisa ser no mínimo em 2x")
                    .validate();
        } catch (NoSuchFieldException e) {
            // TODO: 10/11/20 - Inserir logger
            e.printStackTrace();
            throw new CashControlRuntimeException();
        }

    }

    private static void validateSubscription(Subscription subscription) {
        ValidatorSupport.requiredFields(subscription, ValidatorOperation.CREATE);
    }

    private static void validateCommonsSave(Expense expense) {
        try {
            ValidatorSupport.fieldType(String.class)
                    .target(expense)
                    .operation(ValidatorOperation.CREATE)
                    .checkRequired(CheckRequired.SINGLE)
                    .field("description")
                    .predicate(PredicatesValidator.stringLengthBetween(1, 200))
                    .message("A descricão precisa estrar entre 1 a 200 caracteres")
                    .validate()
                    .field("value", BigDecimal.class)
                    .predicate(PredicatesValidator.bigDecimalFloatMinValue(0.00f))
                    .message("O valor precisa ser maior que de 0.00")
                    .validate()
                    .field("date")
                    .validate()
                    .field("paymentType")
                    .validate()
                    .field("origin.id")
                    .validate()
                    .field("subcategory.id")
                    .validate();

        } catch (NoSuchFieldException e) {
            // TODO: 10/11/20 - Inserir logger
            e.printStackTrace();
            throw new CashControlRuntimeException();
        }
    }
}
