package nuricanozturk.dev.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodWrapper {
    private final Method method;
    private final List<Annotation> annotations;

    public MethodWrapper(Method method) {
        this.method = method;
        annotations = new ArrayList<>();
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void addAnnotation(Annotation annotation) {
        annotations.add(annotation);
    }
}
