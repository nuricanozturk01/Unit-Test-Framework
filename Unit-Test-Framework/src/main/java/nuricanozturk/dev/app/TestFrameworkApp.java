package nuricanozturk.dev.app;

import nuricanozturk.dev.annotation.TestFrameworkApplication;
import nuricanozturk.dev.display.DisplayType;
import nuricanozturk.dev.engine.PackageScanner;
import nuricanozturk.dev.engine.TestEngine;
import nuricanozturk.dev.engine.TestRunner;
import nuricanozturk.dev.exception.TriggerClassNotFoundException;

import java.lang.annotation.Annotation;

import static java.util.Arrays.stream;

public final class TestFrameworkApp {
    private TestFrameworkApp() {
    }

    private static boolean validateMainTestClass(Class<?> $class) {
        return stream($class.getDeclaredAnnotations())
                .map(Annotation::annotationType)
                .map(Class::getSimpleName)
                .anyMatch(name -> name.equals(TestFrameworkApplication.class.getSimpleName()));
    }

    public static void run(Class<?> $class, DisplayType displayType) {

        if (!validateMainTestClass($class))
            throw new TriggerClassNotFoundException("\"TestFrameworkApplication\" annotation not found...");

        var testEngine = new TestEngine.Builder()
                .setTestRunner(new TestRunner(displayType))
                .setPackageScanner(new PackageScanner())
                .build();

        testEngine.startTest();
    }
}
