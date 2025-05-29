/*----------------------------------------------------------------
	FILE		: MethodRunner.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 05.05.2023
	MethodRunner class  run the method according to test types.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import static nuricanozturk.dev.util.ParameterConverter.parseParameterByType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import nuricanozturk.dev.annotation.CsvFile;
import nuricanozturk.dev.annotation.CsvSource;
import nuricanozturk.dev.annotation.DisplayName;
import nuricanozturk.dev.display.IDisplayEngine;
import nuricanozturk.dev.exception.FailedCheckBooleanException;
import nuricanozturk.dev.exception.FailedCheckEqualException;
import nuricanozturk.dev.exception.SourceNotFoundException;

final class MethodRunner implements IMethodRunner {

  private final FileReader fileReader;
  private final IDisplayEngine displayEngine;
  private Class<?> currentClass;

  MethodRunner(final FileReader fileReader, final IDisplayEngine displayEngine) {
    this.fileReader = fileReader;
    this.displayEngine = displayEngine;
  }

  @Override
  public void run(final MethodWrapper method, final Class<?> cls, final Object ctor) {
    this.currentClass = cls;

    var displayName = method.getMethod().getName();
    final var displayNameAnnotation = method.getMethod().getAnnotation(DisplayName.class);

    if (displayNameAnnotation != null) {
      final var value = displayNameAnnotation.value();
      displayName = value.isBlank() ? displayName : value;
    }

    this.displayEngine.displayMethod(displayName);

    if (method.isParameterizedTest()) {
      this.runParameterizedTest(method, ctor, displayName);
    } else if (method.isUnitTest()) {
      this.runUnitTest(method, ctor, displayName);
    } else {
      this.runMethod(method, ctor, displayName);
    }
  }

  private void runMethod(final MethodWrapper method, final Object ctor, final String displayName) {
    final var realMethod = method.getMethod();
    realMethod.setAccessible(true);
    try {
      realMethod.invoke(ctor);
      this.displayEngine.displayUnitTestSuccess(displayName);
    } catch (final InvocationTargetException e) {
      this.displayEngine.displayUnitTestFail(displayName, "", "");
    } catch (final IllegalAccessException e) {
      throw new RuntimeException("METHOD ERROR!", e);
    } finally {
      realMethod.setAccessible(false);
    }
  }

  private void giveMessageParameterizedTest(final Throwable cause, final String displayName) {
    if (cause instanceof FailedCheckBooleanException exception) {
      this.displayEngine.displayParameterizedTestFail(
          displayName, exception.getActual(), exception.getExpected());
    } else if (cause instanceof FailedCheckEqualException exception) {
      this.displayEngine.displayParameterizedTestFail(
          displayName, exception.getExpected(), exception.getActual());
    } else {
      throw new RuntimeException(
          "Error on "
              + displayName
              + " method! in "
              + this.currentClass.getSimpleName()
              + " on parameterized test ",
          cause);
    }
  }

  private void giveMessageUnitTest(final Throwable cause, final String displayName) {
    if (cause instanceof FailedCheckBooleanException exception) {
      this.displayEngine.displayUnitTestFail(
          displayName, exception.getExpected(), exception.getActual());
    } else if (cause instanceof FailedCheckEqualException exception) {
      this.displayEngine.displayUnitTestFail(
          displayName, exception.getExpected(), exception.getActual());
    } else {
      throw new RuntimeException(
          "Error on "
              + displayName
              + " method! in "
              + this.currentClass.getSimpleName()
              + " on unit test ",
          cause);
    }
  }

  private void runUnitTest(
      final MethodWrapper method, final Object constructor, final String displayName) {
    final var realMethod = method.getMethod();
    realMethod.setAccessible(true);
    try {
      realMethod.invoke(constructor);
      this.displayEngine.displayUnitTestSuccess(displayName);
    } catch (final InvocationTargetException e) {
      final Throwable cause = e.getCause();
      this.giveMessageUnitTest(cause, displayName);
    } catch (final IllegalAccessException e) {
      throw new RuntimeException("METHOD ERROR!", e);
    } finally {
      realMethod.setAccessible(false);
    }
  }

  private void runParameterizedTest(
      final MethodWrapper method, final Object constructor, final String displayName) {
    final var realMethod = method.getMethod();
    final var csvParameters = this.getParameters(realMethod).replaceAll("\\s", "").split(",");

    final var paramType = realMethod.getParameterTypes()[0];

    Object parameter = null;

    try {
      realMethod.setAccessible(true);

      for (final var source : csvParameters) {
        parameter = parseParameterByType(source, paramType);
        realMethod.invoke(constructor, parameter);
        this.displayEngine.displayParameterizedTestSuccess(displayName, source);
      }
    } catch (final InvocationTargetException e) {
      final Throwable cause = e.getCause();
      this.giveMessageParameterizedTest(cause, displayName);
    } catch (final IllegalAccessException e) {
      throw new RuntimeException("METHOD ERROR!", e);
    } finally {
      realMethod.setAccessible(false);
    }
  }

  private String getParameters(final Method realMethod) {
    final var csvFile = realMethod.getAnnotation(CsvFile.class);
    final var csvSource = realMethod.getAnnotation(CsvSource.class);

    if (csvFile != null && !csvFile.value().isEmpty() && !csvFile.value().isBlank()) {
      return this.fileReader.readFileCsvFormat(csvFile);
    }

    if (csvSource != null && !csvSource.value().isEmpty() && !csvSource.value().isBlank()) {
      return csvSource.value();
    }

    throw new SourceNotFoundException("Source not found!...");
  }
}
