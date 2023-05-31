package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.CsvFile;
import nuricanozturk.dev.annotation.CsvSource;
import nuricanozturk.dev.annotation.DisplayName;
import nuricanozturk.dev.exception.SourceNotFoundException;

import java.lang.reflect.Method;

import static java.util.Arrays.stream;
import static nuricanozturk.dev.util.ExceptionUtil.handleException;
import static nuricanozturk.dev.util.ParameterConverter.parseParameterByType;

public class MethodRunner implements IMethodRunner{

    private final FileReader m_fileReader;
    private Class<?> m_currentClass;

    public MethodRunner(FileReader m_fileReader) {
        this.m_fileReader = m_fileReader;
    }

    @Override
    public String run(MethodWrapper method, Class<?> $class) {
        m_fileReader.set$class($class);
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

        else runUnitTest(method, ctor);

        return displayName + " named method was runned!\n";
    }


    private void runUnitTest(MethodWrapper method, Object constructor) {
        var realMethod = method.getMethod();
        realMethod.setAccessible(true);
        handleException(() -> realMethod.invoke(constructor), m_currentClass.getSimpleName() +" - " +realMethod.getName() + " " + "Something wrong calling method! Should not have parameters!...", RuntimeException.class, () -> realMethod.setAccessible(false));
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
            return m_fileReader.readFileCsvFormat(csvFile);

        else if (csvSource != null)
            return csvSource.value();

        throw new SourceNotFoundException("Source not found!...");
    }
}