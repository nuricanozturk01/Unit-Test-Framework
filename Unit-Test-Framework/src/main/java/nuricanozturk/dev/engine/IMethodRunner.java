package nuricanozturk.dev.engine;

@FunctionalInterface
interface IMethodRunner
{
    String run(MethodWrapper method, Class<?> $class);
}
