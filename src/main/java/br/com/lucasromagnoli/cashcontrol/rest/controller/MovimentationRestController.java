package br.com.lucasromagnoli.cashcontrol.rest.controller;

import br.com.lucasromagnoli.cashcontrol.movimentation.Movimentation;
import br.com.lucasromagnoli.cashcontrol.rest.commons.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessageSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(RestControllerMapping.PATH_ROOT_MOVIMENTATION)
public class MovimentationRestController {

    @GetMapping(value = {
            RestControllerMapping.PATH_ACTION_NONE,
            RestControllerMapping.PATH_ACTION_ROOT,
            RestControllerMapping.PATH_ACTION_INDEX,
            RestControllerMapping.PATH_ACTION_LIST
    })
    public ResponseEntity<TemplateMessage> index() {
        return TemplateMessageSupport.begin()
                .message("Index")
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(new ArrayList<Movimentation>() {{
                    add(new Movimentation());
                    add(new Movimentation());
                    add(new Movimentation());
                }})
                .build()
                .toResponseEntity();
    }

    @PostMapping(value = {
            RestControllerMapping.PATH_ACTION_NONE,
            RestControllerMapping.PATH_ACTION_ROOT,
            RestControllerMapping.PATH_ACTION_ADD,
            RestControllerMapping.PATH_ACTION_SAVE,
            RestControllerMapping.PATH_ACTION_INSERT
    })
    public ResponseEntity<TemplateMessage> insert(@RequestBody Movimentation movimentation) {
        return TemplateMessageSupport.begin()
                .message("Insert")
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(movimentation)
                .build()
                .toResponseEntity();
    }
}
