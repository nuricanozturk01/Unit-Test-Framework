package nuricanozturk.dev.engine;

@FunctionalInterface
public interface IMethodRunner
{
    String run(MethodWrapper method, Class<?> $class);
}
