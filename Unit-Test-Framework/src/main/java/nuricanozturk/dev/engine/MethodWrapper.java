package nuricanozturk.dev.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodWrapper {
    private final Method method;
    private final List<Annotation> annotations;
    private boolean isParameterizedTest;
    private boolean isUnitTest;

    public MethodWrapper(Method method) {
        this.method = method;
        annotations = new ArrayList<>();
        isParameterizedTest = false;
        isUnitTest = false;

    }

    public boolean isUnitTest() {
        return isUnitTest;
    }

    public void setUnitTest(boolean unitTest) {
        isUnitTest = unitTest;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public Method getMethod() {
        return method;
    }

    public void addAnnotation(Annotation annotation) {
        annotations.add(annotation);
    }

    public boolean isParameterizedTest() {
        return isParameterizedTest;
    }

    public void setParameterizedTest(boolean parameterizedTest) {
        isParameterizedTest = parameterizedTest;
    }
}
