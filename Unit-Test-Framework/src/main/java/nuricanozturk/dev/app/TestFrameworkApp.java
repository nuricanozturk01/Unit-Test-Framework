package nuricanozturk.dev.app;

import nuricanozturk.dev.annotation.TestFrameworkApplication;
import nuricanozturk.dev.display.DisplayType;
import nuricanozturk.dev.engine.PackageScanner;
import nuricanozturk.dev.engine.TestEngine;
import nuricanozturk.dev.engine.TestRunner;
import nuricanozturk.dev.exception.TriggerClassNotFoundException;

import static nuricanozturk.dev.util.validator.AnnotationValidator.validateAnnotation;

public final class TestFrameworkApp {
    private TestFrameworkApp() {
    }
    public static void run(Class<?> $class, DisplayType displayType) {

        if (!validateAnnotation($class, TestFrameworkApplication.class))
            throw new TriggerClassNotFoundException("\"TestFrameworkApplication\" annotation not found...");

        var testEngine = new TestEngine.Builder()
                .setTestRunner(new TestRunner(displayType))
                .setPackageScanner(new PackageScanner())
                .build();

        testEngine.startTest();
    }
}
