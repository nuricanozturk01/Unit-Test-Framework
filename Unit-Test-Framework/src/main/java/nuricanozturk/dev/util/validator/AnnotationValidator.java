package nuricanozturk.dev.util.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class AnnotationValidator {

    public static boolean validateAnnotation(Method method, Class<? extends Annotation> annotation) {
        return method.getAnnotation(annotation) != null;
    }

    public static boolean validateAnnotation(Class<?> $class, Class<? extends Annotation> annotation) {
        return $class.getAnnotation(annotation) != null;
    }
}
