package br.com.lucasromagnoli.cashcontrol.transaction;

import org.springframework.core.convert.converter.Converter;

/**
 * @author github.com/lucasromagnoli
 * @since 11/2020
 */
public class StringToTransactionTypeEnum implements Converter<String, TransactionTypeEnum> {

    @Override
    public TransactionTypeEnum convert(String s) {
        return TransactionTypeEnum.parse(s);
    }
}
