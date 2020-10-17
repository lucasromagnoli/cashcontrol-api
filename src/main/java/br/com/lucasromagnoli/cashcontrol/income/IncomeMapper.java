package br.com.lucasromagnoli.cashcontrol.income;

import br.com.lucasromagnoli.cashcontrol.mapstruct.MapperQualifiers;
import br.com.lucasromagnoli.cashcontrol.mapstruct.StringToBigDecimalAbsolute;
import br.com.lucasromagnoli.cashcontrol.mapstruct.StringToLocalDate;
import br.com.lucasromagnoli.cashcontrol.mapstruct.StringToUpperCase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper( uses = MapperQualifiers.class)
public interface IncomeMapper {
    IncomeMapper INSTANCE = Mappers.getMapper(IncomeMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "date", source = "date", qualifiedBy = StringToLocalDate.class),
            @Mapping(target = "value", source = "value", qualifiedBy = StringToBigDecimalAbsolute.class),
            @Mapping(target = "description", source = "description", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "origin.id", source = "originId"),
            @Mapping(target = "subcategory.id", source = "subcategoryId"),
    })
    Income toUpdate(IncomeDto incomeDto);

    @Mappings({
            @Mapping(target = "date", source = "date", qualifiedBy = StringToLocalDate.class),
            @Mapping(target = "value", source = "value", qualifiedBy = StringToBigDecimalAbsolute.class),
            @Mapping(target = "description", source = "description", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "origin.id", source = "originId"),
            @Mapping(target = "subcategory.id", source = "subcategoryId"),
    })
    Income toSave(IncomeDto incomeDto);

    @Mapping(target = "id", source = "id")
    Income toDelete(IncomeDto incomeDto);
}
