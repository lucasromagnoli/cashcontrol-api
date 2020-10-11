package br.com.lucasromagnoli.cashcontrol.api.rest.v1;

import br.com.lucasromagnoli.cashcontrol.api.templatemessage.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.api.templatemessage.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.api.templatemessage.TemplateMessageSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/origin")
public class OriginRestController {
    
    @GetMapping
    public ResponseEntity<TemplateMessage> index() {

        return TemplateMessageSupport.begin()
                .message("Origin - Index")
                .messageType(MessageTypeEnum.SUCCESS)
                .payload("Payload string")
                .build()
                .toResponseEntity();
    }
}
