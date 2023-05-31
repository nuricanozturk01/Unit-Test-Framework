package nuricanozturk.dev.util.exception;

@FunctionalInterface
public interface ISupplier<T>
{
    T get() throws Exception;
}