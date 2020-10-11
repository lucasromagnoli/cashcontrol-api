package br.com.lucasromagnoli.cashcontrol.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CashControlStaticContextAcessor {
    private static CashControlStaticContextAcessor cashControlStaticContextAcessor;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void registerApplicationContext() {
        cashControlStaticContextAcessor = this;
    }

    public static <T> T getBean(Class<T> clazz) {
        return cashControlStaticContextAcessor.applicationContext.getBean(clazz);
    }

}
