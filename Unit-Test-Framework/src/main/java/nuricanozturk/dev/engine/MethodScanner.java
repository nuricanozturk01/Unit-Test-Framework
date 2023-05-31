package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.*;
import nuricanozturk.dev.util.MethodValidator;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static java.util.Optional.of;

/*
    AfterXX and BeforeXX methods has maximum 2 annotation (extra DisplayName) (max 2)
    UniTest and Display name used single or together (unit test max 2)
    ParamTests must use with  CsvSource, CsvSources or CsvFile and if you want Display Name (max 3)
 */
public class MethodScanner {
    private final List<Optional<MethodWrapper>> m_unitTestMethods;
    private final List<Optional<MethodWrapper>> m_paramTestMethods;
    private final String UNIT_TEST_ANNOTATION = UnitTest.class.getSimpleName();
    private final String PARAMETERIZED_TEST_ANNOTATION = ParameterizedTest.class.getSimpleName();
    private final String CSV_SOURCE_SINGLE = CsvSource.class.getSimpleName();
    private final String CSV_FILE = CsvFile.class.getSimpleName();
    private final LinkedList<MethodWrapper> methodLinkedList;
    private Optional<MethodWrapper> m_beforeEachMethod = empty();
    private Optional<MethodWrapper> m_afterEachMethod = empty();
    private Optional<MethodWrapper> m_beforeAllMethod = empty();
    private Optional<MethodWrapper> m_afterAllMethod = empty();
    private Class<?> currentClass;

    public MethodScanner() {
        m_unitTestMethods = new ArrayList<>();
        m_paramTestMethods = new ArrayList<>();
        methodLinkedList = new LinkedList<>();
    }

    public LinkedList<MethodWrapper> getMethodLinkedList() {
        return methodLinkedList;
    }

    public void prepareAgain(Class<?> $class) {
        currentClass = $class;
        var methodList = stream(currentClass.getDeclaredMethods()).toList();

        methodList.forEach(this::decomposeMethods);
    }

    private void decomposeMethods(Method method) {
        if (method.getDeclaredAnnotations().length == 0)
            return;

        if (method.getDeclaredAnnotations().length == 1)
            insertOnlyOneAnnotations(method);

        else insertTwoOrMoreAnnotations(method);
    }

    private void insertTwoOrMoreAnnotations(Method method) {
        var wrapper = new MethodWrapper(method);

        for (var annotation : method.getDeclaredAnnotations()) {

            var name = annotation.annotationType().getSimpleName();

            if (name.equals(DisplayName.class.getSimpleName()))
                wrapper.addAnnotation(annotation);
            var nameList = wrapper.getAnnotations().stream().map(a -> a.annotationType().getSimpleName()).toList();

            if (name.equals(UNIT_TEST_ANNOTATION) && (!nameList.contains(PARAMETERIZED_TEST_ANNOTATION) ||
                    !nameList.contains(CSV_SOURCE_SINGLE) || !nameList.contains(CSV_FILE)))
                wrapper.addAnnotation(annotation);

            if (name.equals(PARAMETERIZED_TEST_ANNOTATION) && !nameList.contains(UNIT_TEST_ANNOTATION))
                wrapper.addAnnotation(annotation);
        }


    }

    private void insertOnlyOneAnnotations(Method method) {
        var annotation = method.getDeclaredAnnotations()[0];
        var name = annotation.annotationType().getSimpleName();

        if (name.equals(DisplayName.class.getSimpleName())) {
            return;
        } else if (name.equals(BeforeEach.class.getSimpleName())) {
            m_beforeEachMethod = of(new MethodWrapper(method));
            m_beforeEachMethod.get().addAnnotation(annotation);
        } else if (name.equals(BeforeAll.class.getSimpleName())) {
            m_beforeAllMethod = of(new MethodWrapper(method));
            m_beforeAllMethod.get().addAnnotation(annotation);
        } else if (name.equals(AfterEach.class.getSimpleName())) {
            m_afterEachMethod = of(new MethodWrapper(method));
            m_afterEachMethod.get().addAnnotation(annotation);
        } else if (name.equals(AfterAll.class.getSimpleName())) {
            m_afterAllMethod = of(new MethodWrapper(method));
            m_afterAllMethod.get().addAnnotation(annotation);
        } else if (name.equals(UNIT_TEST_ANNOTATION)) {
            var unitTestMethod = new MethodWrapper(method);
            unitTestMethod.addAnnotation(annotation);
            m_unitTestMethods.add(of(unitTestMethod));
        }
    }

    public void prepare(Class<?> $class) {
        var methodList = MethodValidator.validateMethods(stream($class.getDeclaredMethods()).toList());
        //var methodList = stream($class.getDeclaredMethods()).filter(m -> m.getDeclaredAnnotations().length != 0).toList();
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
        var list = new ArrayList<Optional<MethodWrapper>>();

        for (var method : methodList) {
            var annotations = method.getDeclaredAnnotations();

            if (annotations.length < 2)
                continue;

            var nameList = stream(annotations).map(a -> a.annotationType().getSimpleName()).toList();

            if (validaParameterizedTestConditions(method, nameList)) {
                var wrapper = new MethodWrapper(method);
                wrapper.setParameterizedTest(true);
                stream(annotations).forEach(wrapper::addAnnotation);
                list.add(of(wrapper));
            }
        }
        return list;
    }

    private boolean validaParameterizedTestConditions(Method method, List<String> nameList) {
        return isValidParameterForParam(method) && !nameList.contains(UNIT_TEST_ANNOTATION)
                && nameList.contains(PARAMETERIZED_TEST_ANNOTATION)
                && nameList.contains(DisplayName.class.getSimpleName())
                && nameList.contains(CSV_SOURCE_SINGLE) || nameList.contains(CSV_FILE);
    }

    private boolean hasParameter(Method method) {
        return method.getParameterCount() != 0;
    }

    private boolean isValidParameterForParam(Method method) {
        return method.getParameterCount() == 1;
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

        for (var method : methodList) {
            var annotations = method.getDeclaredAnnotations();

            var nameList = stream(annotations).map(a -> a.annotationType().getSimpleName()).toList();

            if (!nameList.contains(PARAMETERIZED_TEST_ANNOTATION) && !hasParameter(method) && nameList.contains(UNIT_TEST_ANNOTATION) &&
                    !nameList.contains(CSV_SOURCE_SINGLE)) {
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
