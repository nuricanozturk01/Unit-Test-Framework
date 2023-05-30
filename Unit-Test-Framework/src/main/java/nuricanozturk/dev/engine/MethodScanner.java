package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class MethodScanner {
    private final LinkedList<MethodWrapper> methodLinkedList;
    private Class<?> currentClass;

    public MethodScanner() {
        methodLinkedList = new LinkedList<>();
    }

    public boolean validateAnnotation(Method method, Class<? extends Annotation> annotation) {
        return stream(method.getDeclaredAnnotations())
                .map(Annotation::annotationType)
                .map(Class::getSimpleName)
                .anyMatch(name -> name.equals(annotation.getSimpleName()));
    }

    public void prepare() {
        System.out.println("Class: " + currentClass.getSimpleName());
        var methods = currentClass.getDeclaredMethods();

        for (var method : methods) {
            var annotations = method.getDeclaredAnnotations();
            var wrapper = new MethodWrapper(method);
            // BEFORE(ALL + EACH) OR AFTER (EACH + ALL) AYRI BAK
            if (validateAnnotation(method, UnitTest.class))
            {
                System.out.println("YES!!!");
                for (var annotation : annotations) {
                    wrapper.addAnnotation(annotation);
                    System.out.println(method.getName() + " - " + annotation.annotationType().getSimpleName());
                }
            }
            else if (validateAnnotation(method, BeforeEach.class) || validateAnnotation(method, BeforeAll.class) ||
                    validateAnnotation(method, AfterEach.class) || validateAnnotation(method, AfterAll.class))
            {
                if (!validateAnnotation(method, UnitTest.class))
                {
                    for (var annotation : annotations) {
                        wrapper.addAnnotation(annotation);
                        System.out.println(method.getName() + " - " + annotation.annotationType().getSimpleName());
                    }
                }
            }
            else {
                methodLinkedList.addLast(wrapper);
                System.out.println("-------------------------\n" + method.getName() + " does not have annotation!!!");
            }


        }

    }

    private boolean isValidBeforeAndAfter(Method method) {
        var list = List.of(BeforeAll.class, BeforeEach.class, AfterAll.class, AfterEach.class);

        return IntStream.range(0, method.getDeclaredAnnotations().length)
                .anyMatch(i -> stream(method.getDeclaredAnnotations()).toList().contains(list.get(i)));

    }

    public Optional<Method> getAfterEachOperations() {
        return Optional.empty();
    }

    public Optional<Method> getAfterAllOperations() {
        return Optional.empty();
    }

    public List<Optional<Method>> getParameterizedTestMethods() {
        return null;
    }

    public Optional<Method> getBeforeEachOperations() {
        return Optional.empty();
    }

    public Optional<Method> getBeforeAllOperations() {
        return Optional.empty();
    }

    public List<Optional<Method>> getUnitTestMethods() {
        return null;
    }

    public MethodWrapper getNextMethod(int i) {
        return methodLinkedList.removeFirst();
    }

    public void setClass(Class<?> $class) {
        currentClass = $class;
    }
}
