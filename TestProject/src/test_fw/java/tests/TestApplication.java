package tests;

import nuricanozturk.dev.annotation.TestFrameworkApplication;
import nuricanozturk.dev.app.TestFrameworkApp;
import nuricanozturk.dev.display.DisplayType;


@TestFrameworkApplication
public class TestApplication {
    public static void main(String[] args) {
        TestFrameworkApp.run(TestApplication.class, DisplayType.Console);
    }
}
