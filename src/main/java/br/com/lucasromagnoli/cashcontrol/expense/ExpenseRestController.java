package br.com.lucasromagnoli.cashcontrol.expense;

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
@RequestMapping("/expense")
public class ExpenseRestController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CashControlSupport cashControlSupport;

    @GetMapping
    public ResponseEntity<TemplateMessage> index(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        Page<Expense> expenses = expenseService.findAll(pageable);
        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie("cashcontrol.messages.operation.list.generic"))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(expenses)
                .build()
                .toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<TemplateMessage> save(@RequestBody ExpenseDto expenseDto) {
        Expense expense = ExpenseMapper.INSTANCE.toSave(expenseDto);
        ExpenseInputValidator.validateSave(expense);
        expenseService.save(expense);
        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.save.success.detailed", expense.getClass().getSimpleName()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(expense)
                .build()
                .toResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<TemplateMessage> delete(@RequestBody ExpenseDto expenseDto) {
        Expense expense = ExpenseMapper.INSTANCE.toDelete(expenseDto);
        ExpenseInputValidator.validateDelete(expense);
        expenseService.delete(expense);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.delete.success.detailed", expense.getId()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(expense)
                .build()
                .toResponseEntity();
    }
}
