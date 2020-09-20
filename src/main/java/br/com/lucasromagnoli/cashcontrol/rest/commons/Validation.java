package br.com.lucasromagnoli.cashcontrol.rest.commons;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import java.util.Map;

@Getter
@JsonPropertyOrder({"validationType", "details"})
public class Validation {
    private final Map<String, String> details;
    private final ValidationType validationType;
    
    public Validation(Map<String, String> details, ValidationType validationType) {
        this.details = details;
        this.validationType = validationType;
    }
}
