/*----------------------------------------------------------------
	FILE		: TestRunner.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	TestRunner class call the method runner than start the test class by class
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import nuricanozturk.dev.display.GraphicalDisplay;
import nuricanozturk.dev.display.IDisplayEngine;

import java.util.stream.IntStream;

import static nuricanozturk.dev.util.exception.ExceptionUtil.handleException;

public final class TestRunner implements ITestRunner
{
    private final IDisplayEngine m_displayEngine;
    private final IMethodRunner m_methodRunner;
    private final MethodScanner m_methodScanner;

    public TestRunner(IDisplayEngine displayEngine)
    {
        m_displayEngine = displayEngine;
        m_methodScanner = new MethodScanner();
        m_methodRunner = new MethodRunner(new FileReader(), m_displayEngine);
    }

    public IDisplayEngine getDisplayEngine()
    {
        return m_displayEngine;
    }

    @Override
    public void run(Class<?> $class)
    {
        m_methodScanner.prepareMethodsForTest($class);

        m_displayEngine.displayClass($class.getSimpleName());

        var ctor = handleException(() -> $class.getDeclaredConstructor().newInstance(),
                "Please be sure used default ctor!...", RuntimeException.class);

        IntStream.range(0, m_methodScanner.getMethodLinkedList().size())
                .mapToObj(m_methodScanner::getNextMethod)
                .forEach(mw -> {
                    m_methodRunner.run(mw, $class, ctor);

                    if (m_displayEngine instanceof GraphicalDisplay)
                        ((GraphicalDisplay) m_displayEngine).finishMethodTest();
                });
    }
}