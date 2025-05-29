/*----------------------------------------------------------------
	FILE		: TestRunner.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	TestRunner class call the method runner than start the test class by class
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import static nuricanozturk.dev.util.exception.ExceptionUtil.handleException;

import java.util.stream.IntStream;
import nuricanozturk.dev.display.DisplayEngineFactory;
import nuricanozturk.dev.display.DisplayType;
import nuricanozturk.dev.display.IDisplayEngine;

public final class TestRunner implements ITestRunner {
  private final IDisplayEngine displayEngine;
  private final IMethodRunner methodRunner;
  private final MethodScanner methodScanner;

  public TestRunner(final DisplayType displayType) {
    this.displayEngine = DisplayEngineFactory.createDisplay(displayType);
    this.methodScanner = new MethodScanner();
    this.methodRunner = new MethodRunner(new FileReader(), this.displayEngine);
  }

  @Override
  public void run(final Class<?> cls) {
    this.methodScanner.prepareMethodsForTest(cls);

    this.displayEngine.displayClass(cls.getSimpleName());

    final var ctor =
        handleException(
            () -> cls.getDeclaredConstructor().newInstance(),
            "Please be sure used default ctor!...",
            RuntimeException.class);

    IntStream.range(0, this.methodScanner.getMethodLinkedList().size())
        .mapToObj(this.methodScanner::getNextMethod)
        .forEach(mw -> this.methodRunner.run(mw, cls, ctor));
  }
}
