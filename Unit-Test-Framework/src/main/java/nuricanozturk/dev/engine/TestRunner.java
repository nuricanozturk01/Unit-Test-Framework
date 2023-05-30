package nuricanozturk.dev.engine;

import nuricanozturk.dev.display.DisplayEngineFactory;
import nuricanozturk.dev.display.DisplayType;
import nuricanozturk.dev.display.IDisplayEngine;

import static nuricanozturk.dev.display.DisplayEngineFactory.createDisplay;
import static nuricanozturk.dev.engine.MethodScanner.*;

public final class TestRunner implements ITestRunner {
    private final IDisplayEngine m_displayEngine;

    public TestRunner(DisplayType displayType) {
        m_displayEngine = createDisplay(displayType);
    }

    @Override
    public void run(Class<?> $class) {
        var beforeEachMethods = getBeforeEachOperations();
        var beforeAllMethods = getBeforeAllOperations();

        var unitTestMethods = getUnitTestMethods();
        var parameterizedUnitTestMethods = getParameterizedTestMethods();

        var afterEachMethods = getAfterEachOperations();
        var afterAllMethods = getAfterAllOperations();
    }

    private void runAfterOperationsIfExists(Class<?> $class) {

    }

    private void runBeforeOperationsIfExists(Class<?> $class) {

    }
}
