package nuricanozturk.dev.engine;

import nuricanozturk.dev.display.DisplayEngineFactory;
import nuricanozturk.dev.display.DisplayType;
import nuricanozturk.dev.display.IDisplayEngine;

import java.util.stream.IntStream;

import static nuricanozturk.dev.util.exception.ExceptionUtil.handleException;

public final class TestRunner implements ITestRunner {
    private final IDisplayEngine m_displayEngine;
    private final IMethodRunner m_methodRunner;
    private final MethodScanner m_methodScanner;

    public TestRunner(DisplayType displayType) {
        m_displayEngine = DisplayEngineFactory.createDisplay(displayType);
        m_methodScanner = new MethodScanner();
        m_methodRunner = new MethodRunner(new FileReader(), m_displayEngine);
    }

    public void test(Class<?> $class) {
        System.out.println("CLASS: " + $class.getSimpleName());
        System.out.println("size: " + m_methodScanner.getMethodLinkedList().size());
        while (!m_methodScanner.getMethodLinkedList().isEmpty()) {
            var method = m_methodScanner.getMethodLinkedList().removeFirst();
            System.out.printf("%s -> ", method.getMethod().getName());
        }
        System.out.println("\n");
        System.out.println("---------------");
    }

    @Override
    public void run(Class<?> $class) {
        m_methodScanner.prepareMethodsForTest($class);
        //test($class);
        m_displayEngine.displayClass($class.getSimpleName());
        var ctor = handleException(() -> $class.getDeclaredConstructor().newInstance(), "Please be sure used default ctor!...", RuntimeException.class);
        IntStream.range(0, m_methodScanner.getMethodLinkedList().size())
                .mapToObj(m_methodScanner::getNextMethod)
                .forEach(mw -> m_methodRunner.run(mw, $class, ctor));
    }
}