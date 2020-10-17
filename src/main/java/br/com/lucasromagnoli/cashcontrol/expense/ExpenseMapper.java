package br.com.lucasromagnoli.cashcontrol.expense;

import br.com.lucasromagnoli.cashcontrol.mapstruct.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper( uses = MapperQualifiers.class)
public interface ExpenseMapper {
    ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

    @Mappings({
            @Mapping(target = "date", source = "date", qualifiedBy = StringToLocalDate.class),
            @Mapping(target = "value", source = "value", qualifiedBy = StringToBigDecimalAbsolute.class),
            @Mapping(target = "description", source = "description", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "paymentType", source = "paymentType", qualifiedBy = StringToPaymentType.class),
            @Mapping(target = "installment.quantity", source = "quantity"),
            @Mapping(target = "installment.amount", source = "amount"),
            @Mapping(target = "subscription.amount", source = "amount"),
            @Mapping(target = "subscription.frequencyType", source = "frequencyType", qualifiedBy = StringToFrequencyType.class),
            @Mapping(target = "origin.id", source = "originId"),
            @Mapping(target = "subcategory.id", source = "subcategoryId"),
    })
    Expense toSave(ExpenseDto expenseDto);

    @Mapping(target = "id", source = "id")
    Expense toDelete(ExpenseDto expenseDto);
}
