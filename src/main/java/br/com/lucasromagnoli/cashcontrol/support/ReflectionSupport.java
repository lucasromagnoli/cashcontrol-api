package br.com.lucasromagnoli.cashcontrol.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionSupport {
    private ReflectionSupport() {
    }

    public static <T> Object getMethod(String field, Object target, Class<T> clazz) {
        // TODO: 10/9/20 - Criar metodo responsável por advinhar o sufixo do método mesmo sem saber se é booleano
        String prefix = clazz.getName()
                .equalsIgnoreCase("Boolean") ? "is" : "get";
        String methodName = prefix + StringSupport.firstLetterToUpperCase(field);
        try {
            Method method = target.getClass().getMethod(methodName);
            return method.getReturnType().cast((method.invoke(target)));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object getMethod(String field, Object target) {
        // TODO: 10/9/20 - Criar metodo responsável por advinhar o sufixo do método mesmo sem saber se é booleano
        String methodName = "get" + StringSupport.firstLetterToUpperCase(field);
        try {
            Method method = target.getClass().getMethod(methodName);
            return method.invoke(target);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> boolean hasAnnotation(T target, String field, Class<? extends Annotation> annotationClass) {
        try {
            return target.getClass().getDeclaredField(field).isAnnotationPresent(annotationClass);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return false;
    }
}
