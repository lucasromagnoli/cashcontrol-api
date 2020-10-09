package br.com.lucasromagnoli.cashcontrol;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlAplication;
import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import br.com.lucasromagnoli.cashcontrol.origin.OriginService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = CashControlAplication.class)
public class OriginServiceTest {

    @Autowired
    private OriginService originService;

    private static Origin originSaved;

    @Test
    @Order(1)
    void loadContexts() {
        Assert.isInstanceOf(OriginService.class, originService);
    }

    @Test
    @Order(2)
    void insertOrigin() {
        // TODO: 10/7/20 - Gerar uma factory para testes
        Origin origin = new Origin();
        origin.setName("Empresa De Tecnologias Ltda");
        originService.save(origin);
        originSaved = origin;

        Assert.notNull(origin.getId(), "O id não pode estar nulo após uma insercão.");
    }

    @Test
    @Order(3)
    void selectOrigin() {
        Assert.notEmpty(originService.findAll(), "É necessário encontrar pelo menos 1 registro");
    }

    @Test
    @Order(4)
    void deleteIncome() {
        Integer originId = originSaved.getId();
        originService.delete(originSaved);
        Assert.state(!originService.existsWithId(originId), "A transacão não foi removida.");
    }
}