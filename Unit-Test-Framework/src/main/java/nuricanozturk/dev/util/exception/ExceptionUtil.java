/*----------------------------------------------------------------
	FILE		: ExceptionUtil.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	ExceptionUtil class for functional exceptions. Get help from CSD
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.util.exception;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

public final class ExceptionUtil {
  private ExceptionUtil() {}

  private static <T extends RuntimeException> void throwException(
      final String msg, final Class<T> cls, final Throwable ex) {
    try {
      throw cls.getConstructor(String.class, Throwable.class).newInstance(msg, ex);
    } catch (final NoSuchMethodException
        | IllegalAccessException
        | InvocationTargetException
        | InstantiationException e) {
      throw new UnsupportedOperationException("Not supported exception");
    }
  }

  public static <T extends RuntimeException> void handleException(
      final IRunnable actionCallback, final String msg, final Class<T> cls) {
    try {
      actionCallback.run();
    } catch (final Throwable ex) {
      throwException(msg, cls, ex);
    }
  }

  public static <T extends RuntimeException> void handleException(
      final IRunnable actionCallback,
      final Consumer<Throwable> consumer,
      final String msg,
      final Class<T> cls) {
    try {
      actionCallback.run();
    } catch (final Throwable ex) {
      consumer.accept(ex);
      throwException(msg, cls, ex);
    }
  }

  public static <T extends RuntimeException, R> R handleException(
      final ISupplier<R> supplier, final String msg, final Class<T> cls) {
    R result = null;

    try {
      result = supplier.get();
    } catch (final Throwable ex) {
      throwException(msg, cls, ex);
    }

    return result;
  }

  public static <T extends RuntimeException, R> R handleException(
      final ISupplier<R> supplier, final Runnable runnable, final String msg, final Class<T> cls) {
    R result = null;

    try {
      result = supplier.get();
    } catch (final Throwable ex) {
      throwException(msg, cls, ex);
    }

    return result;
  }

  public static <T extends RuntimeException, R> R handleException(
      final ISupplier<R> supplier,
      final String msg,
      final Class<T> cls,
      final Runnable finalAction) {
    R result = null;

    try {
      result = supplier.get();
    } catch (final Throwable ex) {
      throwException(msg, cls, ex);
    } finally {
      finalAction.run();
    }

    return result;
  }

  public static <T extends RuntimeException, R> R handleException(
      final ISupplier<R> supplier,
      final Consumer<Throwable> consumer,
      final String msg,
      final Class<T> cls) {
    R result = null;

    try {
      result = supplier.get();
    } catch (final Throwable ex) {
      consumer.accept(ex);
      throwException(msg, cls, ex);
    }

    return result;
  }
}
