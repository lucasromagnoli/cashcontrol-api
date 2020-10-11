package br.com.lucasromagnoli.cashcontrol.origin;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OriginMapper {

    OriginMapper INSTANCE = Mappers.getMapper(OriginMapper.class);

    @Mapping(target = "name", source = "name")
    Origin toModel(OriginDto originDto);

    @InheritInverseConfiguration
    OriginDto toDto(Origin origin);
}
