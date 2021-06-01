package br.com.lucasromagnoli.cashcontrol.configuracao;

import br.com.lucasromagnoli.cashcontrol.common.i18n.Mensagem;
import br.com.lucasromagnoli.cashcontrol.common.i18n.MensagensConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author github.com/lucasromagnoli
 * @since 06/2021
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguracao {
    @Autowired
    private Mensagem mensagem;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(Constantes.Packages.V1_CONTROLLERS))
                .build()
                .apiInfo(construirApiInfo());
    }

    private ApiInfo construirApiInfo() {
        return new ApiInfoBuilder()
                .title(mensagem.get(MensagensConstant.Documentacao.TITULO))
                .description(mensagem.get(MensagensConstant.Documentacao.DESCRICAO))
                .version(mensagem.get(MensagensConstant.Documentacao.VERSAO))
                .license(mensagem.get(MensagensConstant.Documentacao.LICENSA_NOME))
                .licenseUrl(mensagem.get(MensagensConstant.Documentacao.LICENSA_URL))
                .contact(new Contact("Lucas Ramos Romagnoli", "https://github.com/lucasromagnoli", "lucasr.romagnoli@gmail.com"))
                .build();
    }
}
