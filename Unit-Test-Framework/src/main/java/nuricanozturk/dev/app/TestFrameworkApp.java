/*----------------------------------------------------------------
	FILE		: TestFrameworkApp.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	TestFrameworkApp class represent the trigger point for run the test.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.app;

import static nuricanozturk.dev.util.validator.AnnotationValidator.validateAnnotation;

import nuricanozturk.dev.annotation.TestFrameworkApplication;
import nuricanozturk.dev.display.DisplayType;
import nuricanozturk.dev.engine.PackageScanner;
import nuricanozturk.dev.engine.TestEngine;
import nuricanozturk.dev.engine.TestRunner;
import nuricanozturk.dev.exception.TriggerClassNotFoundException;

public final class TestFrameworkApp {
  private TestFrameworkApp() {

  }

  public static void run(final Class<?> cls, final DisplayType displayType) {
    if (!validateAnnotation(cls, TestFrameworkApplication.class)) {
      throw new TriggerClassNotFoundException(
          "\"TestFrameworkApplication\" annotation not found...");
    }

    final var testEngine =
        new TestEngine.Builder()
            .setTestRunner(new TestRunner(displayType))
            .setPackageScanner(new PackageScanner())
            .build();

    testEngine.startTest();
  }
}
