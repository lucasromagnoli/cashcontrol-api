package br.com.lucasromagnoli.cashcontrol.movimentation;

import br.com.lucasromagnoli.cashcontrol.exception.ValidationException;
import br.com.lucasromagnoli.cashcontrol.expense.FrequencyTypeEnum;
import br.com.lucasromagnoli.cashcontrol.origin.Origin;
import br.com.lucasromagnoli.cashcontrol.rest.commons.ValidationType;
import br.com.lucasromagnoli.javaee.useful.support.date.DateSupport;
import br.com.lucasromagnoli.javaee.useful.support.validation.EnumParseException;
import br.com.lucasromagnoli.javaee.useful.support.validation.NumberValidationException;
import br.com.lucasromagnoli.javaee.useful.support.validation.StringValidationException;
import br.com.lucasromagnoli.javaee.useful.support.validation.ValidationSupport;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.math.BigDecimal;
import java.time.format.DateTimeParseException;

public class MovimentationInputValidator {
    private static final MovimentationFields[] saveIncomeRequiredFields = {
            MovimentationFields.VALUE,
            MovimentationFields.DESCRIPTION,
            MovimentationFields.DATE,
            MovimentationFields.ORIGIN_ID
    };

    public static Movimentation validateSave(MovimentationDTO target, Errors errors) {
        validateRequiredFields(errors, MovimentationFields.TYPE);

        Movimentation movimentation = new Movimentation();
        if (!errors.hasErrors()) {
            try {
                movimentation.setType(MovimentationTypeEnum.parse(target.getType()));
            } catch (EnumParseException e) {
                errors.rejectValue("type", "cashcontrol.validations.generic.parser");
            }
        }

        if (MovimentationTypeEnum.INCOME.equals(movimentation.getType())) {
            validateRequiredFields(errors, saveIncomeRequiredFields);
            validateIncomeSave(errors, movimentation, target);
        } else {
            validateRequiredFields(errors, saveIncomeRequiredFields);
            validateExpenseSave(errors, movimentation, target);
        }

        if (errors.hasErrors()) {
            throw new ValidationException(errors.getFieldErrors(), ValidationType.INPUT);
        }

        return movimentation;
    }

    private static void validateIncomeSave(Errors errors, Movimentation movimentation, MovimentationDTO target) {
        if (!errors.hasErrors()) {
            try {
                movimentation.setValue(new BigDecimal(target.getValue()));
                ValidationSupport.numberIsPositive(movimentation.getValue());
                ValidationSupport.stringLengthBetween(1, 200, target.getDescription());
                movimentation.setDescription(target.getDescription());
                movimentation.setType(MovimentationTypeEnum.parse(target.getType()));
                movimentation.setDate(DateSupport.convertFromString(target.getDate()));

                if (target.getFrequency() != null) {
                    movimentation.setFrequencyTypeEnum(FrequencyTypeEnum.parse(target.getFrequency()));
                }

                Origin origin = new Origin();
                movimentation.setOrigin(origin);

                try {
                    origin.setId(Integer.parseInt(target.getOriginId()));
                    ValidationSupport.numberIsPositive(origin.getId());
                } catch (NumberFormatException | NumberValidationException e) {
                    errors.rejectValue("originId", "cashcontrol.validations.generic.parser");
                }

            } catch (NumberFormatException | NumberValidationException e) {
                errors.rejectValue("value", "cashcontrol.validations.generic.parser");
            } catch (StringValidationException e) {
                errors.rejectValue("description", "cashcontrol.validations.generic.parser");
            } catch (DateTimeParseException e) {
                errors.rejectValue("date", "cashcontrol.validations.generic.parser");
            } catch (EnumParseException e) {
                errors.rejectValue("frequency", "cashcontrol.validations.generic.parser");
            }
        }
    }

    private static void validateExpenseSave(Errors errors, Movimentation movimentation, MovimentationDTO target) {
        if (!errors.hasErrors()) {
            try {
                movimentation.setValue(new BigDecimal(target.getValue()));
                ValidationSupport.numberIsPositive(movimentation.getValue());
                ValidationSupport.stringLengthBetween(1, 200, target.getDescription());
                movimentation.setDescription(target.getDescription());
                movimentation.setType(MovimentationTypeEnum.parse(target.getType()));
                movimentation.setDate(DateSupport.convertFromString(target.getDate()));
            } catch (NumberFormatException | NumberValidationException e) {
                errors.rejectValue("value", "cashcontrol.validations.generic.parser");
            } catch (StringValidationException e) {
                errors.rejectValue("description", "cashcontrol.validations.generic.parser");
            } catch (DateTimeParseException e) {
                errors.rejectValue("date", "cashcontrol.validations.generic.parser");
            }
        }
    }

    private static void validateRequiredFields(Errors errors, MovimentationFields... fields) {
        for (MovimentationFields field : fields) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, field.getField(), field.getMessageCode());
        }
    }
}
