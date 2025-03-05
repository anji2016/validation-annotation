package com.validation.validator;

import java.lang.reflect.Method;
import java.util.function.Function;

public class MethodNameUtil {

	public static <T, R> String getFieldNameFromGetter(Function<T, R> getter) {
        try {
            // Infer the class from the getter's type
            Class<T> dtoClass = (Class<T>) getter.getClass().getGenericInterfaces()[0].getClass();

            // Extract the method reference's name
            Method extractedMethod = extractMethodFromLambda(getter, dtoClass);

            // Convert getter method name to field name
            return convertGetterToFieldName(extractedMethod.getName());
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract field name from getter method", e);
        }
    }

    private static <T, R> Method extractMethodFromLambda(Function<T, R> getter, Class<T> dtoClass) throws NoSuchMethodException {
        for (Method method : dtoClass.getMethods()) {
            if (method.getParameterCount() == 0 && method.getReturnType() != void.class) {
                try {
                    getter.apply(null); // Trigger the method reference (expecting NullPointerException)
                } catch (NullPointerException e) {
                    return method; // If NPE, we found the matching method
                } catch (Exception ignored) {}
            }
        }
        throw new NoSuchMethodException("Could not find corresponding getter method in DTO class");
    }

    private static String convertGetterToFieldName(String methodName) {
        if (methodName.startsWith("get")) {
            return Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
        } else if (methodName.startsWith("is")) {
            return Character.toLowerCase(methodName.charAt(2)) + methodName.substring(3);
        }
        throw new IllegalArgumentException("Not a valid getter method: " + methodName);
    }
}
