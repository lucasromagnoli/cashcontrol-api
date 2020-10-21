package br.com.lucasromagnoli.cashcontrol.subcategory;

import br.com.lucasromagnoli.cashcontrol.mapstruct.MapperQualifiers;
import br.com.lucasromagnoli.cashcontrol.mapstruct.StringToUpperCase;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Mapper(uses = MapperQualifiers.class)
public interface SubcategoryMapper {

    SubcategoryMapper INSTANCE = Mappers.getMapper(SubcategoryMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "name", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "description", source = "description", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "category.id", source = "categoryId"),
            @Mapping(target = "id", source = "id")
    })
    Subcategory toUpdate(SubcategoryDto subcategoryDto);

    @Mappings({
            @Mapping(target = "name", source = "name", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "description", source = "description", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "category.id", source = "categoryId")
    })
    Subcategory toSave(SubcategoryDto subcategoryDto);

    @Mapping(target = "id", source = "id")
    Subcategory toDelete(SubcategoryDto subcategoryDto);
}
