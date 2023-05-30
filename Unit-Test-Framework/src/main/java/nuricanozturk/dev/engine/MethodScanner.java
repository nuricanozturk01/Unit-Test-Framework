package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class MethodScanner {
    private final LinkedList<MethodWrapper> methodLinkedList;
    private Class<?> currentClass;

    public MethodScanner() {
        methodLinkedList = new LinkedList<>();
    }

    public LinkedList<MethodWrapper> getMethodLinkedList() {
        return methodLinkedList;
    }

    public void prepare(Class<?> $class) {
        var methodList = stream($class.getDeclaredMethods()).toList();
        currentClass = $class;

        var parameterizedMethods = getParameterizedMethods(methodList);
        var beforeEachMethod = getBeforeEachMethod(methodList);
        var beforeAllMethod = getBeforeAllMethod(methodList);
        var unitTestMethods = prepareUnitTests(methodList);
        var afterEachMethod = getAfterEachMethod(methodList);
        var afterAllMethod = getAfterAllMethod(methodList);


        beforeAllMethod.ifPresent(methodLinkedList::addFirst);

        if (!parameterizedMethods.isEmpty())
            loadParameterizedTest(beforeEachMethod, afterEachMethod, parameterizedMethods);
        if (!unitTestMethods.isEmpty())
            loadUnitTest(beforeEachMethod, afterEachMethod, unitTestMethods);
        afterAllMethod.ifPresent(methodLinkedList::addLast);

        System.out.println("SIZE: " + methodLinkedList.size());
    }

    private void loadUnitTest(Optional<MethodWrapper> beforeEachMethod, Optional<MethodWrapper> afterEachMethod, List<Optional<MethodWrapper>> unitTestMethods) {
        for (var method : unitTestMethods) {
            beforeEachMethod.ifPresent(methodLinkedList::addLast);
            method.ifPresent(methodLinkedList::addLast);
            afterEachMethod.ifPresent(methodLinkedList::addLast);
        }
    }

    private void loadParameterizedTest(Optional<MethodWrapper> beforeEachMethod, Optional<MethodWrapper> afterEachMethod, List<Optional<MethodWrapper>> parameterizedMethods) {

        for (var method : parameterizedMethods) {
            beforeEachMethod.ifPresent(methodLinkedList::addLast);
            method.ifPresent(methodLinkedList::addLast);
            afterEachMethod.ifPresent(methodLinkedList::addLast);
        }

    }

    private Optional<MethodWrapper> getAfterAllMethod(List<Method> methodList) {
        return findMethodViaAnnotation(methodList, AfterAll.class);
    }

    private Optional<MethodWrapper> getAfterEachMethod(List<Method> methodList) {
        return findMethodViaAnnotation(methodList, AfterEach.class);
    }

    private Optional<MethodWrapper> getBeforeAllMethod(List<Method> methodList) {
        return findMethodViaAnnotation(methodList, BeforeAll.class);
    }

    private Optional<MethodWrapper> getBeforeEachMethod(List<Method> methodList) {
        return findMethodViaAnnotation(methodList, BeforeEach.class);
    }

    private List<Optional<MethodWrapper>> getParameterizedMethods(List<Method> methodList) {
        System.out.println("FFFFFFF");
        var list = new ArrayList<Optional<MethodWrapper>>();

        var param = ParameterizedTest.class.getSimpleName();
        var csvSingle = CsvSource.class.getSimpleName();
        var csvMultiple = CsvSources.class.getSimpleName();

        for (var method : methodList) {
            var annotations = method.getDeclaredAnnotations();

            if (annotations.length < 2)
                return Collections.emptyList();

            var nameList = stream(annotations).map(a -> a.annotationType().getSimpleName()).toList();
            if (!nameList.contains(UnitTest.class.getSimpleName()))
                if (isValidParameterForParam(method) && nameList.contains(param) && (nameList.contains(csvSingle) || nameList.contains(csvMultiple))) {
                    var wrapper = new MethodWrapper(method);
                    stream(annotations).forEach(wrapper::addAnnotation);
                    list.add(of(wrapper));
                }
        }
        return list;
    }

    private boolean hasParameter(Method method) {
        return method.getParameterCount() != 0;
    }

    private boolean isValidParameterForParam(Method method) {
        return method.getParameterCount() == 1 && method.getParameterTypes()[0].getSimpleName().equals(String.class.getSimpleName());
    }


    private Optional<MethodWrapper> findMethodViaAnnotation(List<Method> methodList, Class<? extends Annotation> ant) {
        for (var method : methodList) {
            var annotations = method.getDeclaredAnnotations();

            if (annotations.length == 1 && annotations[0].annotationType().getSimpleName().equals(ant.getSimpleName())) {
                var wrapper = new MethodWrapper(method);
                wrapper.addAnnotation(annotations[0]);
                return of(wrapper);
            }
        }
        return empty();
    }


    private List<Optional<MethodWrapper>> prepareUnitTests(List<Method> methodList) {
        var list = new ArrayList<Optional<MethodWrapper>>();

        var unit = UnitTest.class.getSimpleName();
        var csvSingle = CsvSource.class.getSimpleName();
        var csvMultiple = CsvSources.class.getSimpleName();

        for (var method : methodList) {
            var annotations = method.getDeclaredAnnotations();


            var nameList = stream(annotations).map(a -> a.annotationType().getSimpleName()).toList();
            if (!nameList.contains(ParameterizedTest.class.getSimpleName()))
                if (!hasParameter(method) && nameList.contains(unit) && !nameList.contains(csvSingle) && !nameList.contains(csvMultiple)) {
                    var wrapper = new MethodWrapper(method);
                    stream(annotations).forEach(wrapper::addAnnotation);
                    list.add(of(wrapper));
                }
        }
        return list;
    }

    public MethodWrapper getNextMethod(int i) {
        return methodLinkedList.removeFirst();
    }

    public void setClass(Class<?> $class) {
        currentClass = $class;
    }
}
