package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.TestClass;

import java.util.Arrays;
import java.util.List;

public final class TestEngine implements IEngine {
    private final List<Class<?>> m_testClasses;

    public TestEngine(List<Class<?>> annotatedClasses) {
        m_testClasses = annotatedClasses;
    }

    @Override
    public void startTest() {
        var annotatedClasses = decomposeHasTestClassAnnotationClasses();

    }

    @Override
    public List<Class<?>> decomposeHasTestClassAnnotationClasses() {
        return PackageScanner.getClasses().stream().filter(this::filter).toList();
    }

    private boolean filter(Class<?> cl) {
        return Arrays.stream(cl.getDeclaredAnnotations())
                .map(b -> b.annotationType().getSimpleName())
                .anyMatch(a -> a.equals(TestClass.class.getSimpleName()));
    }
}
