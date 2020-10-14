package br.com.lucasromagnoli.cashcontrol.origin;

import br.com.lucasromagnoli.cashcontrol.mapstruct.MapperQualifiers;
import br.com.lucasromagnoli.cashcontrol.mapstruct.StringToUpperCase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper( uses = MapperQualifiers.class)
public interface OriginMapper {

    OriginMapper INSTANCE = Mappers.getMapper(OriginMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "name", qualifiedBy = StringToUpperCase.class),
            @Mapping(target = "id", source = "id")
    })
    Origin toUpdate(OriginDto originDto);

    @Mapping(target = "name", source = "name", qualifiedBy = StringToUpperCase.class)
    Origin toSave(OriginDto originDto);

    @Mapping(target = "id", source = "id")
    Origin toDelete(OriginDto originDto);
}
