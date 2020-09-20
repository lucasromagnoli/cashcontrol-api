package br.com.lucasromagnoli.cashcontrol.movimentation;

import lombok.Getter;

@Getter
enum MovimentationFields {
    VALUE("value", "cashcontrol.validations.generic.required"),
    DESCRIPTION("description", "cashcontrol.validations.generic.required"),
    TYPE("type", "cashcontrol.validations.generic.required"),
    DATE("date", "cashcontrol.validations.generic.required"),
    FREQUENCY("frequency", "cashcontrol.validations.generic.required"),
    ORIGIN_ID("originId", "cashcontrol.validations.generic.required");

    private final String field;
    private final String messageCode;
    
    MovimentationFields(String field, String messageCode) {
        this.field = field;
        this.messageCode = messageCode;
    }
}
