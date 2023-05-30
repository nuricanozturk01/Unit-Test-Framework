package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.TestClass;
import nuricanozturk.dev.exception.TestClassNotFoundException;

import java.util.Arrays;
import java.util.List;

public final class TestEngine implements IEngine {
    private IPackageScanner m_packageScanner;
    private ITestRunner m_testRunner;
    private TestEngine() {}

    @Override
    public void startTest() {
        var annotatedClasses = decomposeHasTestClassAnnotationClasses();
        annotatedClasses.forEach(m_testRunner::run);
    }

    @Override
    public List<Class<?>> decomposeHasTestClassAnnotationClasses() {
        var annotatedClasses = m_packageScanner.getClasses().stream().filter(this::filter).toList();

        if (annotatedClasses.isEmpty())
            throw new TestClassNotFoundException("Test classes not found!");

        return annotatedClasses;
    }

    private boolean filter(Class<?> cl) {
        return Arrays.stream(cl.getDeclaredAnnotations())
                .map(b -> b.annotationType().getSimpleName())
                .anyMatch(a -> a.equals(TestClass.class.getSimpleName()));
    }

    public static class Builder {
        private final TestEngine m_testEngine;

        public Builder() {
            m_testEngine = new TestEngine();
        }

        public Builder setPackageScanner(IPackageScanner packageScanner) {
            m_testEngine.m_packageScanner = packageScanner;
            return this;
        }

        public Builder setTestRunner(ITestRunner testRunner) {
            m_testEngine.m_testRunner = testRunner;
            return this;
        }

        public TestEngine build() {
            return m_testEngine;
        }
    }
}
