package br.com.lucasromagnoli.cashcontrol.category;

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
@RequestMapping("/category")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CashControlSupport cashControlSupport;

    @GetMapping
    public ResponseEntity<TemplateMessage> index(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        Page<Category> categories = categoryService.findAll(pageable);
        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie("cashcontrol.messages.operation.list.generic"))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(categories)
                .build()
                .toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<TemplateMessage> save(@RequestBody CategoryDto categoryDto) {
        Category category = CategoryMapper.INSTANCE.toSave(categoryDto);
        CategoryInputValidator.validateSave(category);
        categoryService.save(category);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.save.success.detailed", category.getName()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(category)
                .build()
                .toResponseEntity();
    }

    @PutMapping
    public ResponseEntity<TemplateMessage> update(@RequestBody CategoryDto categoryDto) {
        Category category = CategoryMapper.INSTANCE.toUpdate(categoryDto);
        CategoryInputValidator.validateUpdate(category);
        categoryService.update(category);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.update.success.detailed", category.getName()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(category)
                .build()
                .toResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<TemplateMessage> delete(@RequestBody CategoryDto categoryDto) {
        Category category = CategoryMapper.INSTANCE.toDelete(categoryDto);
        CategoryInputValidator.validateDelete(category);
        categoryService.delete(category);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.delete.success.detailed", category.getId()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(category)
                .build()
                .toResponseEntity();
    }
}
