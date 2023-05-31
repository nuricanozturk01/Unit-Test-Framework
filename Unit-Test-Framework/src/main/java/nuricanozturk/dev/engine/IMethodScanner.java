package nuricanozturk.dev.engine;

@FunctionalInterface
interface IMethodScanner {
    void prepareMethodsForTest(Class<?> $class);
}
