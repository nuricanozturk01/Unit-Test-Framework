package nuricanozturk.dev.engine;

import java.util.List;

public interface IEngine {

    void startTest();
    List<Class<?>> decomposeHasTestClassAnnotationClasses();

}
