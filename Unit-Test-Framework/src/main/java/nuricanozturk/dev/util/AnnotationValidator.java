package nuricanozturk.dev.util;

import nuricanozturk.dev.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import static java.util.Arrays.stream;

public final class AnnotationValidator {

    public static boolean validateAnnotation(Method method, Class<? extends Annotation> annotation) {
        return stream(method.getDeclaredAnnotations())
                .map(Annotation::annotationType)
                .map(Class::getSimpleName)
                .anyMatch(name -> name.equals(annotation.getSimpleName()));
    }

    public static boolean validateAnnotation(Class<?> $class, Class<? extends Annotation> annotation) {
        return stream($class.getDeclaredAnnotations())
                .map(Annotation::annotationType)
                .map(Class::getSimpleName)
                .anyMatch(name -> name.equals(annotation.getSimpleName()));
    }

    private static boolean hasXAllOrEach(Method method, Class<? extends Annotation> $class) {
        return !validateAnnotation(method, UnitTest.class) && validateAnnotation(method, $class);
    }

    public static boolean hasBeforeAllAnnotation(Method method) {
        return hasXAllOrEach(method, BeforeAll.class);
    }

    public static boolean hasBeforeEachAnnotation(Method method) {
        return hasXAllOrEach(method, BeforeEach.class);
    }

    public static boolean hasAfterAllAnnotation(Method method) {
        return hasXAllOrEach(method, AfterAll.class);
    }

    public static boolean hasAfterEachAnnotation(Method method) {
        return hasXAllOrEach(method, AfterEach.class);
    }

    public static boolean isValidXAllOrEachAnnotations(Method method) {
        return !validateAnnotation(method, UnitTest.class) && (validateAnnotation(method, BeforeEach.class) ||
                validateAnnotation(method, BeforeAll.class) || validateAnnotation(method, AfterAll.class) ||
                validateAnnotation(method, AfterEach.class));
    }

    public static boolean isValidXAllOrEachAnnotations(List<Method> methods) {
        return methods.stream().anyMatch(AnnotationValidator::isValidXAllOrEachAnnotations);
    }
}
