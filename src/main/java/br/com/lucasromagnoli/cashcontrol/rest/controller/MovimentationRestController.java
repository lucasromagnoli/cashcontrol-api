package br.com.lucasromagnoli.cashcontrol.rest.controller;

import br.com.lucasromagnoli.cashcontrol.movimentation.Movimentation;
import br.com.lucasromagnoli.cashcontrol.movimentation.MovimentationDTO;
import br.com.lucasromagnoli.cashcontrol.movimentation.MovimentationInputValidator;
import br.com.lucasromagnoli.cashcontrol.movimentation.MovimentationService;
import br.com.lucasromagnoli.cashcontrol.rest.commons.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestControllerMapping.PATH_ROOT_MOVIMENTATION)
public class MovimentationRestController {
    
    @Autowired
    private MovimentationService movimentationService;
    
    @GetMapping(value = {
            RestControllerMapping.PATH_ACTION_NONE,
            RestControllerMapping.PATH_ACTION_ROOT,
            RestControllerMapping.PATH_ACTION_INDEX,
            RestControllerMapping.PATH_ACTION_LIST
    })
    public ResponseEntity<TemplateMessage> index() {
        return TemplateMessageSupport.begin()
                .message("Listagem das movimentacões")
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(movimentationService.findAll())
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
    public ResponseEntity<TemplateMessage> insert(@RequestBody MovimentationDTO movimentationDTO, BindingResult result) {
        Movimentation movimentation = MovimentationInputValidator.validateSave(movimentationDTO, result);
        movimentationService.save(movimentation);
        return TemplateMessageSupport.begin()
                .message("Insert realizado com sucesso!")
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(movimentation)
                .build()
                .toResponseEntity();
    }
}
