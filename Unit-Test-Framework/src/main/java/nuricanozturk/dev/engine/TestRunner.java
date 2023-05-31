package nuricanozturk.dev.engine;

import nuricanozturk.dev.display.DisplayType;
import nuricanozturk.dev.display.IDisplayEngine;

import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public final class TestRunner implements ITestRunner {
    private final IDisplayEngine m_displayEngine;
    private final IMethodRunner m_methodRunner;
    private final MethodScanner m_methodScanner;

    public TestRunner(DisplayType displayType) {
        m_displayEngine = IDisplayEngine.getInstance(displayType);
        m_methodScanner = new MethodScanner();
        m_methodRunner = new MethodRunner(new FileReader(), m_displayEngine);
    }

    /*public void test(Class<?> $class) {
        System.out.println("CLASS: " + $class.getSimpleName());
        System.out.println("size: " + m_methodScanner.getMethodLinkedList().size());
        while (!m_methodScanner.getMethodLinkedList().isEmpty()) {
            var method = m_methodScanner.getMethodLinkedList().removeFirst();
            System.out.printf("%s -> ", method.getMethod().getName());
        }
        System.out.println("\n");
        System.out.println("---------------");
    }*/

    @Override
    public void run(Class<?> $class) {

        m_methodScanner.prepareMethodsForTest($class);
        // test($class);
        var testMessage = range(0, m_methodScanner.getMethodLinkedList().size())
                .mapToObj(m_methodScanner::getNextMethod)
                .map(mw -> m_methodRunner.run(mw, $class))
                .collect(Collectors.joining());

        //m_displayEngine.display(testMessage);
    }
}