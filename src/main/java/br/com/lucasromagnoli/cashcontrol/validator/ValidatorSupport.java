package br.com.lucasromagnoli.cashcontrol.validator;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlStaticContextAcessor;
import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.support.ReflectionSupport;
import br.com.lucasromagnoli.cashcontrol.support.StringSupport;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.function.Predicate;

public class ValidatorSupport<T> {
    private Object target;
    private String field;
    private Class<T> fieldType;
    private String message;
    private Predicate<T> predicate;
    private ValidatorOperation operation;
    private CheckRequired checkRequired;

    private ValidatorSupport() {
    }

    public static void requiredFields(Object target, ValidatorOperation validatorOperation) {
        Class<?> clazz = target.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Required.class)) {
                Required annotation = field.getAnnotation(Required.class);
                ValidatorOperation[] annotationValue = annotation.operations();

                for (ValidatorOperation operation : annotationValue) {
                    if (operation.equals(validatorOperation) || operation.equals(ValidatorOperation.ALL)) {
                        if (Objects.isNull(ReflectionSupport.getMethod(field.getName(), target))) {
                            throw new InputValidationException(field,
                                    CashControlStaticContextAcessor.getBean(CashControlSupport.class)
                                            .getPropertie("cashcontrol.validation.input.required.field"));
                        }
                    }
                }
            }
        }
    }

    public void requiredField(Object fieldValue) throws NoSuchFieldException {
        String tmpFieldName = getFieldName();
        if (ReflectionSupport.hasAnnotation(target, tmpFieldName, Required.class)) {
            Required annotation = target.getClass().getDeclaredField(tmpFieldName).getAnnotation(Required.class);
            ValidatorOperation[] annotationValue = annotation.operations();
            if (ArrayUtils.contains(annotationValue, operation)) {
                if (Objects.isNull(fieldValue)) {
                    throw new InputValidationException(StringSupport.camelToSnake(field),
                            CashControlStaticContextAcessor.getBean(CashControlSupport.class)
                                    .getPropertie("cashcontrol.validation.input.required.field"));
                }
            }
        }
    }

    public static <T> ValidatorSupport<T> fieldType(Class<T> fieldType) {
        ValidatorSupport<T> validatorSupport = new ValidatorSupport<>();
        validatorSupport.fieldType = fieldType;
        return validatorSupport;
    }

    public ValidatorSupport<T> target(Object target) {
        this.target = target;
        return this;
    }

    public ValidatorSupport<T> field(String field) {
        this.field = field;
        return this;
    }

    public ValidatorSupport<T> message(String message) {
        this.message = message;
        return this;
    }

    public ValidatorSupport<T> predicate(Predicate<T> predicate) {
        this.predicate = predicate;
        return this;
    }

    public ValidatorSupport<T> operation(ValidatorOperation validatorOperation) {
        this.operation = validatorOperation;
        return this;
    }

    public ValidatorSupport<T> checkRequired(CheckRequired checkRequired) {
        this.checkRequired = checkRequired;
        return this;
    }

    public <O> ValidatorSupport<O> field(String field, Class<O> fieldType) {
        ValidatorSupport<O> validatorSupport = new ValidatorSupport<>();
        validatorSupport.fieldType = fieldType;
        validatorSupport.target = this.target;
        validatorSupport.operation = this.operation;
        validatorSupport.field = field;
        validatorSupport.checkRequired = this.checkRequired;
        return validatorSupport;
    }

    public ValidatorSupport<T> validate() throws NoSuchFieldException {
        Object fieldValue = getFieldValue();

        if (CheckRequired.SINGLE.equals(checkRequired)) {
            requiredField(fieldValue);
        } else if (CheckRequired.ALL.equals(checkRequired)) {
            requiredFields(target, operation);
        }

        if (!Objects.isNull(fieldValue) && !Objects.isNull(predicate) && !predicate.test(fieldType.cast(fieldValue))) {
            String messageException = message;
            if (Objects.isNull(message) && ReflectionSupport.hasAnnotation(target, field, Required.class)) {
                String annotationMessage
                        = target.getClass().getDeclaredField(field).getAnnotation(Required.class).message();
                if (!StringUtils.isEmpty(annotationMessage)) {
                    messageException = annotationMessage;
                }
            }
            throw new InputValidationException(field, messageException);
        }

        clearToNextValidation();
        return this;
    }

    private void clearToNextValidation() {
        field = null;
        message = null;
        predicate = null;
    }

    private Object getFieldValue() {
        if (!field.contains(".")) {
            return ReflectionSupport.getMethod(field, target, fieldType);
        } else {
            String[] fields = StringUtils.split(field, ".");
            if (fields.length == 2) {
                Object entity = ReflectionSupport.getMethod(fields[0], target);
                if (!Objects.isNull(entity)) {
                    return ReflectionSupport.getMethod(fields[1], entity);
                }
            }
        }

        return null;
    }

    private String getFieldName() {
        if (!field.contains(".")) {
            return field;
        } else {
            return StringUtils.split(field, ".")[0];
        }
    }
}
