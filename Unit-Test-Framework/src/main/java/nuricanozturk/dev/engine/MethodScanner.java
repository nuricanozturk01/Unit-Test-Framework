package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.*;
import nuricanozturk.dev.util.validator.MethodValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static java.util.Optional.of;

class MethodScanner implements IMethodScanner {
    private final LinkedList<MethodWrapper> methodLinkedList;

    public MethodScanner() {
        methodLinkedList = new LinkedList<>();
    }

    public LinkedList<MethodWrapper> getMethodLinkedList() {
        return methodLinkedList;
    }


    @Override
    public void prepareMethodsForTest(Class<?> $class) {
        var methodList = MethodValidator.validateMethods(stream($class.getDeclaredMethods()).toList());

        var parameterizedMethods = getParameterizedMethods(methodList);
        var beforeEachMethod = findMethodByAnnotation(methodList, BeforeEach.class);
        var beforeAllMethod = findMethodByAnnotation(methodList, BeforeAll.class);
        var afterEachMethod = findMethodByAnnotation(methodList, AfterEach.class);
        var afterAllMethod = findMethodByAnnotation(methodList, AfterAll.class);
        var unitTestMethods = prepareUnitTests(methodList);

        beforeAllMethod.ifPresent(methodLinkedList::addFirst);

        if (!parameterizedMethods.isEmpty())
            loadParameterizedTest(beforeEachMethod, afterEachMethod, parameterizedMethods);

        if (!unitTestMethods.isEmpty())
            loadUnitTest(beforeEachMethod, afterEachMethod, unitTestMethods);

        afterAllMethod.ifPresent(methodLinkedList::addLast);
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

    private List<Optional<MethodWrapper>> getParameterizedMethods(List<Method> methodList) {
        var list = new ArrayList<Optional<MethodWrapper>>();

        for (var method : methodList) {

            if (method.getDeclaredAnnotations().length < 2)
                continue;

            var paramAnnotation = method.getAnnotation(ParameterizedTest.class);
            var unitAnnotation = method.getAnnotation(UnitTest.class);
            var displayAnnotation = method.getAnnotation(DisplayName.class);
            var csvFileAnnotation = method.getAnnotation(CsvFile.class);
            var csvSourceAnnotation = method.getAnnotation(CsvSource.class);

            if (isValidParameterForParam(method) && unitAnnotation == null && paramAnnotation != null &&
                    (csvFileAnnotation != null || csvSourceAnnotation != null)) {

                var wrapper = new MethodWrapper(method);
                wrapper.addAnnotation(paramAnnotation);

                if (csvFileAnnotation != null)
                    wrapper.addAnnotation(csvFileAnnotation);
                if (csvSourceAnnotation != null)
                    wrapper.addAnnotation(csvSourceAnnotation);
                if (displayAnnotation != null)
                    wrapper.addAnnotation(displayAnnotation);

                wrapper.setParameterizedTest(true);
                list.add(of(wrapper));
            }
        }
        return list;
    }

    private boolean hasParameter(Method method) {
        return method.getParameterCount() != 0;
    }

    private boolean isValidParameterForParam(Method method) {
        return method.getParameterCount() == 1;
    }


    private Optional<MethodWrapper> findMethodByAnnotation(List<Method> methodList, Class<? extends Annotation> ant) {
        for (var method : methodList) {

            if (method.getDeclaredAnnotations().length == 0)
                continue;


            var annotation = method.getAnnotation(ant);

            if (annotation != null) {
                var displayName = method.getAnnotation(DisplayName.class);
                var wrapper = new MethodWrapper(method);

                wrapper.addAnnotation(annotation);
                if (displayName != null)
                    wrapper.addAnnotation(displayName);

                return of(wrapper);
            }
        }
        return empty();
    }


    private List<Optional<MethodWrapper>> prepareUnitTests(List<Method> methodList) {

        var list = new ArrayList<Optional<MethodWrapper>>();

        for (var method : methodList) {

            if (method.getDeclaredAnnotations().length == 0)
                continue;

            var paramAnnotation = method.getAnnotation(ParameterizedTest.class);
            var unitAnnotation = method.getAnnotation(UnitTest.class);
            var displayAnnotation = method.getAnnotation(DisplayName.class);
            var csvFileAnnotation = method.getAnnotation(CsvFile.class);
            var csvSourceAnnotation = method.getAnnotation(CsvSource.class);

            if (!hasParameter(method) && paramAnnotation == null && csvFileAnnotation == null && csvSourceAnnotation == null && unitAnnotation != null) {
                var wrapper = new MethodWrapper(method);
                wrapper.addAnnotation(unitAnnotation);

                if (displayAnnotation != null)
                    wrapper.addAnnotation(displayAnnotation);

                list.add(of(wrapper));
            }
        }
        return list;
    }

    public MethodWrapper getNextMethod(int i) {
        return methodLinkedList.removeFirst();
    }
}
