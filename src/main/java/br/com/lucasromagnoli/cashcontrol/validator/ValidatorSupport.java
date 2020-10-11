package br.com.lucasromagnoli.cashcontrol.validator;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlStaticContextAcessor;
import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.exception.InputValidationException;
import br.com.lucasromagnoli.cashcontrol.support.ReflectionSupport;
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

    private boolean nullSafe;

    private ValidatorSupport() {
    }

    private void requiredFields() {
        Class<?> clazz = target.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Required.class)) {
                Required annotation = field.getAnnotation(Required.class);
                ValidatorOperation[] annotationValue = annotation.operations();

                for (ValidatorOperation operation : annotationValue) {
                    if (operation.equals(this.operation) || operation.equals(ValidatorOperation.ALL)) {
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

    public static <T> void requiredFields(T target) {
        Class<?> clazz = target.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Required.class)) {
                if (Objects.isNull(ReflectionSupport.getMethod(field.getName(), target))) {
                    throw new InputValidationException(field);
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

    public ValidatorSupport<T> nullSafe() {
        this.nullSafe = true;
        return this;
    }

    public void validate() throws NoSuchFieldException {
        if (!Objects.isNull(operation)) {
            requiredFields();
        }

        T fieldValue = ReflectionSupport.getMethod(field, target, fieldType);

        if (nullSafe) {
            if (Objects.isNull(fieldValue)) {
                throw new InputValidationException(field,
                        CashControlStaticContextAcessor.getBean(CashControlSupport.class)
                                .getPropertie("cashcontrol.validation.input.nullsafe"));
            }
        }

        if (!predicate.test(fieldValue)) {
            String messageException = message;
            if (Objects.isNull(message) && ReflectionSupport.hasAnnotation(target, field, Required.class)) {
                String annotationMessage
                        = target.getClass().getDeclaredField(field).getAnnotation(Required.class).message();
                if (!StringUtils.isEmpty(annotationMessage)) {
                    messageException = annotationMessage;
                }
            }
            throw new InputValidationException(messageException);
        }
    }
}
