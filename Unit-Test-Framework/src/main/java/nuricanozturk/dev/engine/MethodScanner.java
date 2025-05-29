/*----------------------------------------------------------------
	FILE		: MethodScanner.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	MethodScanner class scan the packages and decompose the annotations.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import nuricanozturk.dev.annotation.AfterAll;
import nuricanozturk.dev.annotation.AfterEach;
import nuricanozturk.dev.annotation.BeforeAll;
import nuricanozturk.dev.annotation.BeforeEach;
import nuricanozturk.dev.annotation.CsvFile;
import nuricanozturk.dev.annotation.CsvSource;
import nuricanozturk.dev.annotation.DisplayName;
import nuricanozturk.dev.annotation.ParameterizedTest;
import nuricanozturk.dev.annotation.UnitTest;
import nuricanozturk.dev.util.validator.MethodValidator;

final class MethodScanner implements IMethodScanner {
  private final LinkedList<MethodWrapper> methodLinkedList;

  MethodScanner() {
    this.methodLinkedList = new LinkedList<>();
  }

  public LinkedList<MethodWrapper> getMethodLinkedList() {
    return this.methodLinkedList;
  }

  @Override
  public void prepareMethodsForTest(final Class<?> cls) {
    final var methodList =
        MethodValidator.validateMethods(stream(cls.getDeclaredMethods()).toList());

    final var parameterizedMethods = this.getParameterizedMethods(methodList);
    final var beforeEachMethod = this.findMethodByAnnotation(methodList, BeforeEach.class);
    final var beforeAllMethod = this.findMethodByAnnotation(methodList, BeforeAll.class);
    final var afterEachMethod = this.findMethodByAnnotation(methodList, AfterEach.class);
    final var afterAllMethod = this.findMethodByAnnotation(methodList, AfterAll.class);
    final var unitTestMethods = this.prepareUnitTests(methodList);

    beforeAllMethod.ifPresent(this.methodLinkedList::addFirst);

    if (!parameterizedMethods.isEmpty()) {
      this.loadParameterizedTest(beforeEachMethod, afterEachMethod, parameterizedMethods);
    }

    if (!unitTestMethods.isEmpty()) {
      this.loadUnitTest(beforeEachMethod, afterEachMethod, unitTestMethods);
    }

    afterAllMethod.ifPresent(this.methodLinkedList::addLast);
  }

  private void loadUnitTest(
      final Optional<MethodWrapper> beforeEachMethod,
      final Optional<MethodWrapper> afterEachMethod,
      final List<Optional<MethodWrapper>> unitTestMethods) {
    for (final var method : unitTestMethods) {
      beforeEachMethod.ifPresent(this.methodLinkedList::addLast);
      method.ifPresent(this.methodLinkedList::addLast);
      afterEachMethod.ifPresent(this.methodLinkedList::addLast);
    }
  }

  private void loadParameterizedTest(
      final Optional<MethodWrapper> beforeEachMethod,
      final Optional<MethodWrapper> afterEachMethod,
      final List<Optional<MethodWrapper>> parameterizedMethods) {

    for (final var method : parameterizedMethods) {
      beforeEachMethod.ifPresent(this.methodLinkedList::addLast);
      method.ifPresent(this.methodLinkedList::addLast);
      afterEachMethod.ifPresent(this.methodLinkedList::addLast);
    }
  }

  private List<Optional<MethodWrapper>> getParameterizedMethods(final List<Method> methodList) {
    final var list = new ArrayList<Optional<MethodWrapper>>();

    for (final var method : methodList) {
      if (method.getDeclaredAnnotations().length < 2) {
        continue;
      }

      final var paramAnnotation = method.getAnnotation(ParameterizedTest.class);
      final var unitAnnotation = method.getAnnotation(UnitTest.class);
      final var displayAnnotation = method.getAnnotation(DisplayName.class);
      final var csvFileAnnotation = method.getAnnotation(CsvFile.class);
      final var csvSourceAnnotation = method.getAnnotation(CsvSource.class);

      if (this.isValidParameterForParam(method)
          && unitAnnotation == null
          && paramAnnotation != null
          && (csvFileAnnotation != null || csvSourceAnnotation != null)) {
        final var wrapper = new MethodWrapper(method);
        wrapper.addAnnotation(paramAnnotation);

        if (csvFileAnnotation != null) {
          wrapper.addAnnotation(csvFileAnnotation);
        }
        if (csvSourceAnnotation != null) {
          wrapper.addAnnotation(csvSourceAnnotation);
        }
        if (displayAnnotation != null) {
          wrapper.addAnnotation(displayAnnotation);
        }

        wrapper.setParameterizedTest(true);
        list.add(of(wrapper));
      }
    }
    return list;
  }

  private boolean hasParameter(final Method method) {
    return method.getParameterCount() != 0;
  }

  private boolean isValidParameterForParam(final Method method) {
    return method.getParameterCount() == 1;
  }

  private Optional<MethodWrapper> findMethodByAnnotation(
      final List<Method> methodList, final Class<? extends Annotation> ant) {
    for (final var method : methodList) {

      if (method.getDeclaredAnnotations().length == 0) {
        continue;
      }

      final var annotation = method.getAnnotation(ant);

      if (annotation != null) {
        final var displayName = method.getAnnotation(DisplayName.class);
        final var wrapper = new MethodWrapper(method);

        wrapper.addAnnotation(annotation);
        if (displayName != null) {
          wrapper.addAnnotation(displayName);
        }

        return of(wrapper);
      }
    }
    return empty();
  }

  private List<Optional<MethodWrapper>> prepareUnitTests(final List<Method> methodList) {

    final var list = new ArrayList<Optional<MethodWrapper>>();

    for (final var method : methodList) {

      if (method.getDeclaredAnnotations().length == 0) {
        continue;
      }

      final var paramAnnotation = method.getAnnotation(ParameterizedTest.class);
      final var unitAnnotation = method.getAnnotation(UnitTest.class);
      final var displayAnnotation = method.getAnnotation(DisplayName.class);
      final var csvFileAnnotation = method.getAnnotation(CsvFile.class);
      final var csvSourceAnnotation = method.getAnnotation(CsvSource.class);

      if (!this.hasParameter(method)
          && paramAnnotation == null
          && csvFileAnnotation == null
          && csvSourceAnnotation == null
          && unitAnnotation != null) {
        final var wrapper = new MethodWrapper(method);
        wrapper.addAnnotation(unitAnnotation);

        if (displayAnnotation != null) {
          wrapper.addAnnotation(displayAnnotation);
        }

        wrapper.setUnitTest(true);
        list.add(of(wrapper));
      }
    }
    return list;
  }

  public MethodWrapper getNextMethod(final int i) {
    return this.methodLinkedList.removeFirst();
  }
}
