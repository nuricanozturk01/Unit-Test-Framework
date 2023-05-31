package nuricanozturk.dev.util;

@FunctionalInterface
public interface ISupplier<T>
{
    T get() throws Exception;
}