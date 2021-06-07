package br.com.lucasromagnoli.cashcontrol.configuracao.factory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;

import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.DATE_FORMAT;
import static br.com.lucasromagnoli.cashcontrol.configuracao.Constantes.DATE_TIME_FORMAT;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public class Jackson2ObjectMapperFactory {
    private Jackson2ObjectMapperFactory() {
    }

    public static Jackson2ObjectMapperBuilderCustomizer criar2JacksonObjectMapper() {
        return builder -> {
            builder.serializationInclusion(JsonInclude.Include.NON_NULL)
                    .featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES)
                    .featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                    .featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                    .featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_INDEX)
                    .simpleDateFormat(DATE_TIME_FORMAT)
                    .serializers(new LocalDateSerializer(ofPattern(DATE_FORMAT)))
                    .serializers(new LocalDateTimeSerializer(ofPattern(DATE_TIME_FORMAT)))
                    .deserializers(new LocalDateDeserializer(ofPattern(DATE_FORMAT)))
                    .deserializers(new LocalDateTimeDeserializer(ofPattern(DATE_TIME_FORMAT)))
                    .visibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                    .visibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
                    .visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        };
    }
}
