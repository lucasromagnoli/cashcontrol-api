package br.com.lucasromagnoli.cashcontrol.rest.controller;

import br.com.lucasromagnoli.cashcontrol.movimentation.Movimentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestControllerMapping.PATH_ROOT_MOVIMENTATION)
public class MovimentationRestController {

    @GetMapping(value = {
            RestControllerMapping.PATH_ACTION_NONE, 
            RestControllerMapping.PATH_ACTION_ROOT, 
            RestControllerMapping.PATH_ACTION_INDEX, 
            RestControllerMapping.PATH_ACTION_LIST
    })
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Index");
    }

    @PostMapping(value = {
            RestControllerMapping.PATH_ACTION_NONE,
            RestControllerMapping.PATH_ACTION_ROOT,
            RestControllerMapping.PATH_ACTION_ADD,
            RestControllerMapping.PATH_ACTION_SAVE,
            RestControllerMapping.PATH_ACTION_INSERT
    })
    public ResponseEntity<String> insert(@RequestBody Movimentation movimentation) {
        return ResponseEntity.ok("Insert");
    }
}
