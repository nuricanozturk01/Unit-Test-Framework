package nuricanozturk.dev.app;

import nuricanozturk.dev.engine.TestEngine;

import static nuricanozturk.dev.engine.PackageScanner.getClasses;

public class TestFrameworkApp {
    public static void run(Class<?> $class) {

        var testClasses = getClasses();
        var testEngine = new TestEngine(testClasses);
        testEngine.startTest();
    }
}
