package br.com.lucasromagnoli.cashcontrol.origin;

import br.com.lucasromagnoli.cashcontrol.api.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.api.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.api.TemplateMessageSupport;
import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@RestController
@RequestMapping("/origin")
public class OriginRestController {

    @Autowired
    private OriginService originService;

    @Autowired
    private CashControlSupport cashControlSupport;

    @GetMapping
    public ResponseEntity<TemplateMessage> index(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        Page<Origin> origins = originService.findAll(pageable);
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
