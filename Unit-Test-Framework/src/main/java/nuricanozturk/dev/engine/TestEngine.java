/*----------------------------------------------------------------
	FILE		: TestEngine.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	TestEngine class prepare classes for test.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.TestClass;
import nuricanozturk.dev.display.GraphicalDisplay;
import nuricanozturk.dev.exception.TestClassNotFoundException;

import java.util.List;

public final class TestEngine implements IEngine
{
    private IPackageScanner m_packageScanner;
    private ITestRunner m_testRunner;

    private TestEngine()
    {
    }

    @Override
    public void startTest()
    {
        var annotatedClasses = decomposeHasTestClassAnnotationClasses();

        for (var cls : annotatedClasses)
        {
            m_testRunner.run(cls);

            if (m_testRunner.getDisplayEngine() instanceof GraphicalDisplay)
                ((GraphicalDisplay) m_testRunner.getDisplayEngine()).finishClassTest();
        }

        if (m_testRunner.getDisplayEngine() instanceof GraphicalDisplay)
            ((GraphicalDisplay) m_testRunner.getDisplayEngine()).finishTest();
    }

    @Override
    public List<Class<?>> decomposeHasTestClassAnnotationClasses()
    {
        var annotatedClasses = m_packageScanner.getClasses()
                .stream()
                .filter(cl -> cl.getAnnotation(TestClass.class) != null)
                .toList();

        if (annotatedClasses.isEmpty())
            throw new TestClassNotFoundException("Test classes not found!");

        return annotatedClasses;
    }

    public static class Builder
    {
        private final TestEngine m_testEngine;

        public Builder()
        {
            m_testEngine = new TestEngine();
        }

        public Builder setPackageScanner(IPackageScanner packageScanner)
        {
            m_testEngine.m_packageScanner = packageScanner;
            return this;
        }

        public Builder setTestRunner(ITestRunner testRunner)
        {
            m_testEngine.m_testRunner = testRunner;
            return this;
        }

        public TestEngine build()
        {
            return m_testEngine;
        }
    }
}
