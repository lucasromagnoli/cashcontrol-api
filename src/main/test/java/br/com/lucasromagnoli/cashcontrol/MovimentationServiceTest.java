package br.com.lucasromagnoli.cashcontrol;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlAplication;
import br.com.lucasromagnoli.cashcontrol.movimentation.MovimentationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = CashControlAplication.class)
public class MovimentationServiceTest {
    
    @Autowired
    private MovimentationService movimentationService;
    
    @Test
    void loadContexts() {
        Assert.isInstanceOf(MovimentationService.class, movimentationService);
    }
}
