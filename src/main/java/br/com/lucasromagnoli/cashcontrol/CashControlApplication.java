package br.com.lucasromagnoli.cashcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@SpringBootApplication
public class CashControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashControlApplication.class, args);
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
