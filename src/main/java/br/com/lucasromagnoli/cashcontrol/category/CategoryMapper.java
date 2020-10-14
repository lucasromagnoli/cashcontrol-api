package br.com.lucasromagnoli.cashcontrol.category;

import br.com.lucasromagnoli.cashcontrol.mapstruct.MapperQualifiers;
import br.com.lucasromagnoli.cashcontrol.mapstruct.StringToTransactionType;
import br.com.lucasromagnoli.cashcontrol.mapstruct.StringToUpperCase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper( uses = MapperQualifiers.class)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "name", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "description", source = "description", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "type", source = "type", qualifiedBy = StringToTransactionType.class),
            @Mapping(target = "id", source = "id")
    })
    Category toUpdate(CategoryDto categoryDto);

    @Mappings({
            @Mapping(target = "name", source = "name", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "description", source = "description", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "type", source = "type", qualifiedBy = StringToTransactionType.class),
    })
    Category toSave(CategoryDto categoryDto);

    @Mapping(target = "id", source = "id")
    Category toDelete(CategoryDto categoryDto);
}
