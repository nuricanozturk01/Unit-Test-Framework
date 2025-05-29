/*----------------------------------------------------------------
	FILE		: MethodValidator.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	MethodValidator class validate methods and check the method-annotation rules.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.util.validator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import nuricanozturk.dev.annotation.AfterAll;
import nuricanozturk.dev.annotation.AfterEach;
import nuricanozturk.dev.annotation.BeforeAll;
import nuricanozturk.dev.annotation.BeforeEach;
import nuricanozturk.dev.annotation.CsvFile;
import nuricanozturk.dev.annotation.CsvSource;
import nuricanozturk.dev.annotation.ParameterizedTest;
import nuricanozturk.dev.annotation.UnitTest;

public final class MethodValidator {
  private static final int THREE = 3;
  private static final int TWO = 2;

  private MethodValidator() {

  }

  public static List<Method> validateMethods(final List<Method> methodList) {
    return methodList.stream().filter(MethodValidator::isValidMethod).toList();
  }

  private static boolean isValidMethod(final Method method) {
    final var annotations =
        Arrays.stream(method.getDeclaredAnnotations())
            .map(annotation -> annotation.annotationType().getSimpleName())
            .toList();

    if (annotations.isEmpty()) {
      return false;
    }

    final boolean hasBeforeEach = method.isAnnotationPresent(BeforeEach.class);
    final boolean hasBeforeAll = method.isAnnotationPresent(BeforeAll.class);
    final boolean hasAfterEach = method.isAnnotationPresent(AfterEach.class);
    final boolean hasAfterAll = method.isAnnotationPresent(AfterAll.class);
    final boolean hasUnit = method.isAnnotationPresent(UnitTest.class);
    final boolean hasParam = method.isAnnotationPresent(ParameterizedTest.class);
    final boolean hasCsvSource = method.isAnnotationPresent(CsvSource.class);
    final boolean hasCsvFile = method.isAnnotationPresent(CsvFile.class);

    if (annotations.size() <= THREE
        && hasParam
        && (hasCsvFile || hasCsvSource)
        && !hasBeforeAll
        && !hasAfterAll
        && !hasUnit
        && !hasBeforeEach
        && !hasAfterEach) {
      return true;
    }

    if (annotations.size() <= TWO
        && hasUnit
        && !hasBeforeAll
        && !hasAfterAll
        && !hasParam
        && !hasBeforeEach
        && !hasAfterEach
        && !hasCsvFile
        && !hasCsvSource) {
      return true;
    }

    if (annotations.size() <= TWO
        && (hasAfterAll || hasBeforeAll)
        && !hasUnit
        && !hasParam
        && !hasBeforeEach
        && !hasAfterEach) {
      return true;
    }

    return annotations.size() <= TWO
        && (hasBeforeEach || hasAfterEach)
        && !hasUnit
        && !hasParam
        && !hasAfterAll
        && !hasBeforeAll;
  }
}
