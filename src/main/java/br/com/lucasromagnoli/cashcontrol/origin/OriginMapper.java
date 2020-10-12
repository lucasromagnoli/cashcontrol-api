package br.com.lucasromagnoli.cashcontrol.origin;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OriginMapper {

    OriginMapper INSTANCE = Mappers.getMapper(OriginMapper.class);

    @Mappings({
            @Mapping(target = "name", source = "name", qualifiedByName = "nameToUpperCase"),
            @Mapping(target = "id", source = "id")
    })
    Origin toUpdate(OriginDto originDto);

    @Mapping(target = "name", source = "name", qualifiedByName = "nameToUpperCase")
    Origin toSave(OriginDto originDto);

    @Mapping(target = "id", source = "id")
    Origin toDelete(OriginDto originDto);

    @Named("nameToUpperCase")
    static String nameToUpperCase(String name) {
        return name.toUpperCase();
    }
}
