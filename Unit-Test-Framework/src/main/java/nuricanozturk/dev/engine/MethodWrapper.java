package nuricanozturk.dev.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodWrapper {
    private final Method method;
    private final List<Annotation> annotations;
    private boolean isParameterizedTest;

    public MethodWrapper(Method method) {
        this.method = method;
        annotations = new ArrayList<>();
        isParameterizedTest = false;

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
