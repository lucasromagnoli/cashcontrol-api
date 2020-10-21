package br.com.lucasromagnoli.cashcontrol.subcategory;

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
@RequestMapping("/subcategory")
public class SubcategoryRestController {
    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private CashControlSupport cashControlSupport;

    @GetMapping
    public ResponseEntity<TemplateMessage> index(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        Page<Subcategory> subcategories = subcategoryService.findAll(pageable);
        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie("cashcontrol.messages.operation.list.generic"))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(subcategories)
                .build()
                .toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<TemplateMessage> save(@RequestBody SubcategoryDto subcategoryDto) {
        Subcategory subcategory = SubcategoryMapper.INSTANCE.toSave(subcategoryDto);
        SubcategoryInputValidator.validateSave(subcategory);
        subcategoryService.save(subcategory);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.save.success.detailed", subcategory.getName()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(subcategory)
                .build()
                .toResponseEntity();
    }

    @PutMapping
    public ResponseEntity<TemplateMessage> update(@RequestBody SubcategoryDto subcategoryDto) {
        Subcategory subcategory = SubcategoryMapper.INSTANCE.toUpdate(subcategoryDto);
        SubcategoryInputValidator.validateUpdate(subcategory);
        subcategoryService.update(subcategory);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.update.success.detailed", subcategory.getName()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(subcategory)
                .build()
                .toResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<TemplateMessage> delete(@RequestBody SubcategoryDto subcategoryDto) {
        Subcategory subcategory = SubcategoryMapper.INSTANCE.toDelete(subcategoryDto);
        SubcategoryInputValidator.validateDelete(subcategory);
        subcategoryService.delete(subcategory);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.delete.success.detailed", subcategory.getId()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(subcategory)
                .build()
                .toResponseEntity();
    }
}
