package br.com.lucasromagnoli.cashcontrol.mapstruct;

import br.com.lucasromagnoli.cashcontrol.support.DateSupport;
import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

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
