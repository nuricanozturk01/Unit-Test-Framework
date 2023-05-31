package nuricanozturk.dev.util.exception;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

public final class ExceptionUtil {
    private ExceptionUtil() {
    }

    private static <T extends RuntimeException> void throwException(String msg, Class<T> cls, Throwable ex) {
        try {
            throw cls.getConstructor(String.class, Throwable.class).newInstance(msg, ex);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                 InstantiationException e) {
            throw new UnsupportedOperationException("Not supported exception");
        }
    }

    public static <T extends RuntimeException> void handleException(IRunnable actionCallback, String msg, Class<T> cls) {
        try {
            actionCallback.run();
        } catch (Throwable ex) {
            throwException(msg, cls, ex);
        }
    }

    public static <T extends RuntimeException> void handleException(IRunnable actionCallback, Consumer<Throwable> consumer, String msg,
                                                                    Class<T> cls) {
        try {
            actionCallback.run();
        } catch (Throwable ex) {
            consumer.accept(ex);
            throwException(msg, cls, ex);
        }
    }

    public static <T extends RuntimeException, R> R handleException(ISupplier<R> supplier, String msg, Class<T> cls) {
        R result = null;

        try {
            result = supplier.get();
        } catch (Throwable ex) {
            throwException(msg, cls, ex);
        }

        return result;
    }

    public static <T extends RuntimeException, R> R handleException(ISupplier<R> supplier, Runnable runnable, String msg, Class<T> cls) {
        R result = null;

        try {
            result = supplier.get();

        } catch (Throwable ex) {
            throwException(msg, cls, ex);
        }

        return result;
    }

    public static <T extends RuntimeException, R> R handleException(ISupplier<R> supplier, String msg, Class<T> cls, Runnable finalAction) {
        R result = null;

        try {
            result = supplier.get();
        } catch (Throwable ex) {
            System.out.println(msg);
            //throwException(msg, cls, ex);
        } finally {
            finalAction.run();
        }

        return result;
    }

    public static <T extends RuntimeException, R> R handleException(ISupplier<R> supplier, Consumer<Throwable> consumer,
                                                                    String msg, Class<T> cls) {
        R result = null;

        try {
            result = supplier.get();
        } catch (Throwable ex) {
            consumer.accept(ex);
            throwException(msg, cls, ex);
        }

        return result;
    }
}