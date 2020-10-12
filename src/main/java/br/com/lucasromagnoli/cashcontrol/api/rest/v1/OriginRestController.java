package br.com.lucasromagnoli.cashcontrol.api.rest.v1;

import br.com.lucasromagnoli.cashcontrol.api.templatemessage.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.api.templatemessage.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.api.templatemessage.TemplateMessageSupport;
import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.origin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/origin")
public class OriginRestController {

    @Autowired
    private OriginService originService;

    @Autowired
    private CashControlSupport cashControlSupport;

    @GetMapping
    public ResponseEntity<TemplateMessage> index() {
        List<Origin> origins = originService.findAll();
        // TODO: 10/12/20 - Implementar paginacão
        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie("cashcontrol.messages.operation.list.generic"))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(origins)
                .build()
                .toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<TemplateMessage> save(@RequestBody OriginDto originDto) {
        Origin origin = OriginMapper.INSTANCE.toSave(originDto);
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

    @PutMapping
    public ResponseEntity<TemplateMessage> update(@RequestBody OriginDto originDto) {
        Origin origin = OriginMapper.INSTANCE.toUpdate(originDto);
        OriginInputValidator.validateUpdate(origin);
        originService.update(origin);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.update.success.detailed", origin.getName()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(origin)
                .build()
                .toResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<TemplateMessage> delete(@RequestBody OriginDto originDto) {
        Origin origin = OriginMapper.INSTANCE.toDelete(originDto);
        OriginInputValidator.validateDelete(origin);
        originService.delete(origin);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.delete.success.detailed", origin.getId()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(origin)
                .build()
                .toResponseEntity();
    }
}
