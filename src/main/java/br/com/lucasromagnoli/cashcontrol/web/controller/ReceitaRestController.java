package br.com.lucasromagnoli.cashcontrol.web.controller;

import br.com.lucasromagnoli.cashcontrol.dominio.entidade.Receita;
import br.com.lucasromagnoli.cashcontrol.dominio.negocio.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.lucasromagnoli.cashcontrol.web.controller.configuracao.ControllerMapping.ROOT_RECEITA;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
@RestController
@RequestMapping(ROOT_RECEITA)
public class ReceitaRestController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<List<Receita>> listarReceitas() {
        Receita receita = new Receita();
        receita.setDescricao("Descricão Aleatória!");
        receitaService.cadastrar(receita);

        return ResponseEntity.ok(receitaService.listar());
    }

    @PostMapping
    public ResponseEntity<String> cadastrarReceitas() {
        return ResponseEntity.ok("cadastrarReceitas");
    }

    @PutMapping
    public ResponseEntity<String> atualizarReceitas() {
        return ResponseEntity.ok("atualizarReceitas");
    }

    @DeleteMapping
    public ResponseEntity<String> removerReceitas() {
        return ResponseEntity.ok("removerReceitas");
    }
}
