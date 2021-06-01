package br.com.lucasromagnoli.cashcontrol.common.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Component
public class Mensagem {
    @Autowired
    private MessageSource messageSource;

    public String get(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public String get(String code, Object... argumentos) {
        return messageSource.getMessage(code, argumentos, LocaleContextHolder.getLocale());
    }
}
