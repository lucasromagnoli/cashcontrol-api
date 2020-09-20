package br.com.lucasromagnoli.cashcontrol.rest.controller;

import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import br.com.lucasromagnoli.cashcontrol.origin.OriginDTO;
import br.com.lucasromagnoli.cashcontrol.origin.OriginService;
import br.com.lucasromagnoli.cashcontrol.rest.commons.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/origin")
public class OriginRestController {
    
    @Autowired
    private OriginService originService;
    
    @GetMapping
    public ResponseEntity<TemplateMessage> index() {
        return TemplateMessageSupport.begin()
                .messageType(MessageTypeEnum.SUCCESS)
                .message("Listagem das origens")
                .payload(originService.findAll())
                .build()
                .toResponseEntity();
    }
    
    @PostMapping
    public ResponseEntity<TemplateMessage> insert(@RequestBody OriginDTO originDTO) {
        Origin origin = new Origin();
        origin.setName(originDTO.getName());
        
        originService.save(origin);
        
        return TemplateMessageSupport
                .begin()
                .payload(origin)
                .build()
                .toResponseEntity();
    }
}
