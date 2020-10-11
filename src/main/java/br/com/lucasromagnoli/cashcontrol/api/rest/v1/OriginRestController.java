package br.com.lucasromagnoli.cashcontrol.api.rest.v1;

import br.com.lucasromagnoli.cashcontrol.api.templatemessage.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.api.templatemessage.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.api.templatemessage.TemplateMessageSupport;
import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.origin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/origin")
public class OriginRestController {

    @Autowired
    private OriginService originService;

    @Autowired
    private CashControlSupport cashControlSupport;

    @GetMapping
    public ResponseEntity<TemplateMessage> index() {

        return TemplateMessageSupport.begin()
                .message("Origin - Index")
                .messageType(MessageTypeEnum.SUCCESS)
                .payload("Payload string")
                .build()
                .toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<TemplateMessage> save(@RequestBody OriginDto originDto) {
        Origin origin = OriginMapper.INSTANCE.toModel(originDto);
        OriginInputValidator.validateSave(origin);
        originService.save(origin);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.save.success.detailed", origin.getName()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(origin)
                .build()
                .toResponseEntity();
    }
}
