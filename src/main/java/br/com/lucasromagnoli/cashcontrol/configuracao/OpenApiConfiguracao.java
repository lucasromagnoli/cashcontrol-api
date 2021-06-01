package br.com.lucasromagnoli.cashcontrol.configuracao;

import br.com.lucasromagnoli.cashcontrol.common.i18n.Mensagem;
import br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Configuration
public class OpenApiConfiguracao {
    @Autowired
    private Mensagem mensagem;

    @Bean
    public GroupedOpenApi v1Api() {
        return GroupedOpenApi.builder()
                .group("V1")
                .packagesToScan(Constantes.Packages.V1_CONTROLLERS)
                .pathsToMatch("/v1/**")
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().title(mensagem.get(MensagensConstant.Documentacao.TITULO))
                        .description(mensagem.get(MensagensConstant.Documentacao.DESCRICAO))
                        .version(mensagem.get(MensagensConstant.Documentacao.VERSAO))
                        .license(new License()
                                .name(mensagem.get(MensagensConstant.Documentacao.LICENSA_NOME))
                                .url(mensagem.get(MensagensConstant.Documentacao.LICENSA_URL)))
                        .contact(new Contact()
                                .email("lucasr.romagnoli@gmail.com")
                                .name("Lucas Ramos Romagnoli")
                                .url("https://github.com/lucasromagnoli"))
                );
    }
}
