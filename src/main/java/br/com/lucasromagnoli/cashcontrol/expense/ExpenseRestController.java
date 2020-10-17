package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.api.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.api.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.api.TemplateMessageSupport;
import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense")
public class ExpenseRestController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CashControlSupport cashControlSupport;

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
}
