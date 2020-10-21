package br.com.lucasromagnoli.cashcontrol.mapstruct;

import br.com.lucasromagnoli.cashcontrol.expense.FrequencyTypeEnum;
import br.com.lucasromagnoli.cashcontrol.expense.PaymentTypeEnum;
import br.com.lucasromagnoli.cashcontrol.support.DateSupport;
import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@MapperQualifier
public class MapperQualifiers {

    @StringToUpperCase
    public String stringToUpperCase(String s) {
        return StringUtils.isBlank(s) ? null : s.toUpperCase();
    }

    @StringToTransactionType
    public TransactionTypeEnum stringToTransactionType (String s) {
        return TransactionTypeEnum.parse(s);
    }

    @StringToPaymentType
    public PaymentTypeEnum stringToPaymentType (String s) {
        return PaymentTypeEnum.parse(s);
    }

    @StringToFrequencyType
    public FrequencyTypeEnum stringToFrequencyType (String s) {
        return FrequencyTypeEnum.parse(s);
    }

    @StringToBigDecimalAbsolute
    public BigDecimal stringToBigDecimalAbsolute(String s) {
        try {
            return new BigDecimal(s).abs();
        } catch (RuntimeException e) {
            return null;
        }
    }

    @StringToLocalDate
    public LocalDate stringToLocalDate(String s) {
        return DateSupport.fromString(s, "dd/MM/yyyy");
    }
}
