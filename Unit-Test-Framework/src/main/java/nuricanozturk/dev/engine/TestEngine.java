/*----------------------------------------------------------------
	FILE		: TestEngine.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	TestEngine class prepare classes for test.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import java.util.List;
import nuricanozturk.dev.annotation.TestClass;
import nuricanozturk.dev.exception.TestClassNotFoundException;

public final class TestEngine implements IEngine {
  private IPackageScanner packageScanner;
  private ITestRunner testRunner;

  private TestEngine() {}

  @Override
  public void startTest() {
    final var annotatedClasses = this.decomposeHasTestClassAnnotationClasses();
    annotatedClasses.forEach(this.testRunner::run);
  }

  @Override
  public List<Class<?>> decomposeHasTestClassAnnotationClasses() {
    final var annotatedClasses =
        this.packageScanner.getClasses().stream()
            .filter(cl -> cl.getAnnotation(TestClass.class) != null)
            .toList();

    if (annotatedClasses.isEmpty()) {
      throw new TestClassNotFoundException("Test classes not found!");
    }

    return annotatedClasses;
  }

  public static class Builder {
    private final TestEngine testEngine;

    public Builder() {
      this.testEngine = new TestEngine();
    }

    public Builder setPackageScanner(final IPackageScanner packageScanner) {
      this.testEngine.packageScanner = packageScanner;
      return this;
    }

    public Builder setTestRunner(final ITestRunner testRunner) {
      this.testEngine.testRunner = testRunner;
      return this;
    }

    public TestEngine build() {
      return this.testEngine;
    }
  }
}
