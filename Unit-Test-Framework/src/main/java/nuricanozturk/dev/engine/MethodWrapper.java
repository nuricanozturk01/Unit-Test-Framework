package nuricanozturk.dev.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MethodWrapper {
    private final Method method;
    private final List<Annotation> annotations;
    private boolean isParameterizedTest;
    //private final PriorityQueue<Annotation> priorityQueue;

    public MethodWrapper(Method method) {
      //  priorityQueue = new PriorityQueue<>();
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
        //priorityQueue.offer(annotation);
    }

    public boolean isParameterizedTest() {
        return isParameterizedTest;
    }

    public void setParameterizedTest(boolean parameterizedTest) {
        isParameterizedTest = parameterizedTest;
    }
}
