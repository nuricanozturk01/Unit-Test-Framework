package nuricanozturk.dev.engine;

@FunctionalInterface
public interface IMethodScanner {
    void prepareMethodsForTest(Class<?> $class);
}
