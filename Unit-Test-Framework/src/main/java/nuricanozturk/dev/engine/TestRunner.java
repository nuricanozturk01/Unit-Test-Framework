package nuricanozturk.dev.engine;

import nuricanozturk.dev.display.DisplayType;
import nuricanozturk.dev.display.IDisplayEngine;

import static java.util.stream.IntStream.range;
import static nuricanozturk.dev.display.DisplayEngineFactory.createDisplay;

public final class TestRunner implements ITestRunner {
    private final IDisplayEngine m_displayEngine;
    private final MethodRunner m_methodRunner;
    private final MethodScanner m_methodScanner;

    public TestRunner(DisplayType displayType) {
        m_displayEngine = createDisplay(displayType);
        m_methodScanner = new MethodScanner();
        m_methodRunner = new MethodRunner();
    }

    @Override
    public void run(Class<?> $class) {
        m_methodScanner.setClass($class);
        m_methodScanner.prepare();
        var testMessage = range(0, $class.getDeclaredMethods().length)
                .mapToObj(m_methodScanner::getNextMethod)
                .map(m_methodRunner::run)
                .toString();

        m_displayEngine.display(testMessage);
    }
}
