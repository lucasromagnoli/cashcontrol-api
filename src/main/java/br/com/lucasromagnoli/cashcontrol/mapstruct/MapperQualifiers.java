package br.com.lucasromagnoli.cashcontrol.mapstruct;

import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import org.apache.commons.lang3.StringUtils;

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
}
