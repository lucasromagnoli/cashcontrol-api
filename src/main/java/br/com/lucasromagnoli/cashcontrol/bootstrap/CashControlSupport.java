package br.com.lucasromagnoli.cashcontrol.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource(value = "classpath:cashcontrol-validations.properties", encoding = "UTF-8")
@PropertySource(value = "classpath:cashcontrol-configurations.properties", encoding = "UTF-8")
public class CashControlSupport {
    
    @Autowired
    Environment environment;
    
    public String getPropertie(String key) {
        return environment.getProperty(key);
    }
}
