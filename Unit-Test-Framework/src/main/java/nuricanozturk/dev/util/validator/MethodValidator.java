package nuricanozturk.dev.util.validator;

import nuricanozturk.dev.annotation.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public final class MethodValidator {

    public static List<Method> validateMethods(List<Method> methodList) {
        return methodList.stream().filter(MethodValidator::isValidMethod).toList();
    }

    private static boolean isValidMethod(Method method) {
        var annotations = Arrays.stream(method.getDeclaredAnnotations())
                .map(annotation -> annotation.annotationType().getSimpleName())
                .toList();

        if (annotations.isEmpty())
            return false;

        boolean hasBeforeEach = method.isAnnotationPresent(BeforeEach.class);
        boolean hasBeforeAll = method.isAnnotationPresent(BeforeAll.class);
        boolean hasAfterEach = method.isAnnotationPresent(AfterEach.class);
        boolean hasAfterAll = method.isAnnotationPresent(AfterAll.class);
        boolean hasUnit = method.isAnnotationPresent(UnitTest.class);
        boolean hasParam = method.isAnnotationPresent(ParameterizedTest.class);
        boolean hasCsvSource = method.isAnnotationPresent(CsvSource.class);
        boolean hasCsvFile = method.isAnnotationPresent(CsvFile.class);

        if (annotations.size() <= 3 && hasParam && (hasCsvFile || hasCsvSource) && !hasBeforeAll && !hasAfterAll && !hasUnit && !hasBeforeEach && !hasAfterEach)
            return true;

        if (annotations.size() <= 2 && hasUnit && !hasBeforeAll && !hasAfterAll && !hasParam && !hasBeforeEach && !hasAfterEach && !hasCsvFile && !hasCsvSource)
            return true;


        if (annotations.size() <= 2 && (hasAfterAll || hasBeforeAll) && !hasUnit && !hasParam && !hasBeforeEach && !hasAfterEach)
            return true;

        return annotations.size() <= 2 && (hasBeforeEach || hasAfterEach) && !hasUnit && !hasParam && !hasAfterAll && !hasBeforeAll;
    }
}
