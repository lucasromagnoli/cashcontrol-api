package br.com.lucasromagnoli.cashcontrol.income;

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
@RequestMapping("/income")
public class IncomeRestController {
    @Autowired
    private IncomeService incomeService;

    @Autowired
    private CashControlSupport cashControlSupport;

    @GetMapping
    public ResponseEntity<TemplateMessage> index(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        Page<Income> incomes = incomeService.findAll(pageable);
        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie("cashcontrol.messages.operation.list.generic"))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(incomes)
                .build()
                .toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<TemplateMessage> save(@RequestBody IncomeDto incomeDto) {
        Income income = IncomeMapper.INSTANCE.toSave(incomeDto);
        IncomeInputValidator.validateSave(income);
        incomeService.save(income);
        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.save.success.detailed", income.getClass().getSimpleName()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(income)
                .build()
                .toResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<TemplateMessage> delete(@RequestBody IncomeDto incomeDto) {
        Income income = IncomeMapper.INSTANCE.toDelete(incomeDto);
        IncomeInputValidator.validateDelete(income);
        incomeService.delete(income);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie(
                        "cashcontrol.messages.operation.delete.success.detailed", income.getId()))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(income)
                .build()
                .toResponseEntity();
    }
}
