package br.com.lucasromagnoli.cashcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

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
}
