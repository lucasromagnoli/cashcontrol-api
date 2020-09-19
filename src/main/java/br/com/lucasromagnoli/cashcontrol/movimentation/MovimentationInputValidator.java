package br.com.lucasromagnoli.cashcontrol.movimentation;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



@Component
public class MovimentationInputValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Validator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "cashcontrol.validations.generic.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "cashcontrol.validations.generic.required");
    }
    
    public void validateSave(Object target, Errors errors) {
        validate(target, errors);
    }
}
