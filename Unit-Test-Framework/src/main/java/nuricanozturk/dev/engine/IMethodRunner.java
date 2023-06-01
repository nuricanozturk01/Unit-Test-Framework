package nuricanozturk.dev.engine;

@FunctionalInterface
interface IMethodRunner
{
    void run(MethodWrapper method, Class<?> $class);
}
