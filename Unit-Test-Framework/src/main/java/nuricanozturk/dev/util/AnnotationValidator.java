package nuricanozturk.dev.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

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
}
