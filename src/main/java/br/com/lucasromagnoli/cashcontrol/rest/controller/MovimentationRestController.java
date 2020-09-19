package br.com.lucasromagnoli.cashcontrol.rest.controller;

import br.com.lucasromagnoli.cashcontrol.exception.ValidationException;
import br.com.lucasromagnoli.cashcontrol.movimentation.Movimentation;
import br.com.lucasromagnoli.cashcontrol.movimentation.MovimentationDTO;
import br.com.lucasromagnoli.cashcontrol.movimentation.MovimentationInputValidator;
import br.com.lucasromagnoli.cashcontrol.rest.commons.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessageSupport;
import br.com.lucasromagnoli.cashcontrol.rest.commons.ValidationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(RestControllerMapping.PATH_ROOT_MOVIMENTATION)
public class MovimentationRestController {
    
    @Autowired
    private MovimentationInputValidator movimentationInputValidator;
    
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
    public ResponseEntity<TemplateMessage> insert(@RequestBody MovimentationDTO movimentation, BindingResult result) {
        
        movimentationInputValidator.validateSave(movimentation, result);
        
        if (result.hasErrors()) {
            throw new ValidationException(result.getFieldErrors(), ValidationType.INPUT);
        }
        
        return TemplateMessageSupport.begin()
                .message("Insert")
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(movimentation)
                .build()
                .toResponseEntity();
    }
}
