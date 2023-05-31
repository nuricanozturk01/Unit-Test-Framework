package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.CsvFile;
import nuricanozturk.dev.annotation.CsvSource;
import nuricanozturk.dev.annotation.DisplayName;
import nuricanozturk.dev.display.ConsoleDisplay;
import nuricanozturk.dev.display.IDisplayEngine;
import nuricanozturk.dev.exception.FailedTestException;
import nuricanozturk.dev.exception.SourceNotFoundException;

import java.lang.reflect.Method;

import static java.util.Arrays.stream;
import static nuricanozturk.dev.util.exception.ExceptionUtil.handleException;
import static nuricanozturk.dev.util.ParameterConverter.parseParameterByType;

class MethodRunner implements IMethodRunner{

    private final FileReader m_fileReader;
    private Class<?> m_currentClass;
    private final IDisplayEngine m_displayEngine;

    public MethodRunner(FileReader m_fileReader, IDisplayEngine displayEngine) {
        this.m_fileReader = m_fileReader;
        m_displayEngine = displayEngine;
    }

    @Override
    public String run(MethodWrapper method, Class<?> $class) {
        m_currentClass = $class;

        var displayName = method.getMethod().getName();
        var displayNameAnnotation = method.getMethod().getAnnotation(DisplayName.class);

        if (displayNameAnnotation != null) {
            var value = displayNameAnnotation.value();
            displayName = value.isBlank() || value.isEmpty() ? displayName : value;
        }

        var ctor = handleException(() -> $class.getDeclaredConstructor().newInstance(), "Please be sure use default ctor!...", RuntimeException.class);

        if (method.isParameterizedTest())
            runParameterizedTest(method, ctor);

        else runUnitTest(method, ctor, displayName);

        return displayName + " named method was runned!\n";
    }


    private void runUnitTest(MethodWrapper method, Object constructor, String displayName) {
        var realMethod = method.getMethod();
        realMethod.setAccessible(true);
        handleException(() -> realMethod.invoke(constructor),
                ((ConsoleDisplay)m_displayEngine).getFailText(displayName + " test was failed..."),
                RuntimeException.class,
                () -> {
                    realMethod.setAccessible(false);
                    //m_displayEngine.displaySuccess(displayName + " tes success!..");
                });
    }

    private void runUnitTest(Method realMethod, String displayName) {
        realMethod.setAccessible(false);
        m_displayEngine.displaySuccess(displayName + " test was success!");
    }

    private void runParameterizedTest(MethodWrapper method, Object constructor) {
        var realMethod = method.getMethod();
        var csvParameters = getParameters(realMethod).split(",");

        realMethod.setAccessible(true);

        var paramType = realMethod.getParameterTypes()[0];

        stream(csvParameters).map(source -> parseParameterByType(source, paramType))
                .forEach(param -> handleException(() -> realMethod.invoke(constructor, param), "Something wrong calling method! Check Parameters!...", RuntimeException.class));

        realMethod.setAccessible(false);
    }

    private String getParameters(Method realMethod) {
        var csvFile = realMethod.getAnnotation(CsvFile.class);
        var csvSource = realMethod.getAnnotation(CsvSource.class);


        if (csvFile != null)
            if (!csvFile.value().isEmpty() && !csvFile.value().isBlank())
                return m_fileReader.readFileCsvFormat(csvFile);

        if (csvSource != null)
            if (!csvSource.value().isEmpty() && !csvSource.value().isBlank())
                return csvSource.value();

        throw new SourceNotFoundException("Source not found!...");
    }
}