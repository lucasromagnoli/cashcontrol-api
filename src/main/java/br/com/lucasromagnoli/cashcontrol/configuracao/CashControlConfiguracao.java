package br.com.lucasromagnoli.cashcontrol.configuracao;

import br.com.lucasromagnoli.cashcontrol.configuracao.factory.Jackson2ObjectMapperFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@Configuration
public class CashControlConfiguracao {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return Jackson2ObjectMapperFactory.criar2JacksonObjectMapper();
    }

    @Bean
    public MessageSource messageSource() {
        final var source = new ReloadableResourceBundleMessageSource();
        source.setDefaultEncoding(StandardCharsets.UTF_8.name());
        source.setBasename("classpath:i18n/cashcontrol-mensagens");
        return source;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("pt", "BR"));
        return sessionLocaleResolver;
    }
}
