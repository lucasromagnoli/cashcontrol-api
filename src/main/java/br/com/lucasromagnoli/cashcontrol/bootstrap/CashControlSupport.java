package br.com.lucasromagnoli.cashcontrol.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:cashcontrol-configurations.properties")
public class CashControlSupport {
    
    @Autowired
    Environment environment;
    
    public String getPropertie(String key) {
        return environment.getProperty(key);
    }
}
