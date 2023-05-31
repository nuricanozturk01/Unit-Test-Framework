package nuricanozturk.dev.engine;

import java.util.List;

interface IEngine {

    void startTest();
    List<Class<?>> decomposeHasTestClassAnnotationClasses();
}
