package nuricanozturk.dev.engine;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

public class MethodScanner {
    public static Optional<Method> getBeforeEachOperations() {
        return Optional.empty();
    }

    public static Optional<Method> getBeforeAllOperations() {
        return Optional.empty();
    }

    public static List<Optional<Method>> getUnitTestMethods() {
        return null;
    }

    public static Optional<Method> getAfterEachOperations() {
        return Optional.empty();
    }

    public static Optional<Method> getAfterAllOperations() {
        return Optional.empty();
    }

    public static List<Optional<Method>> getParameterizedTestMethods() {
        return null;
    }
}
