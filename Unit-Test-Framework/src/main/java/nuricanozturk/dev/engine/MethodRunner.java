package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.CsvFile;
import nuricanozturk.dev.annotation.CsvSource;
import nuricanozturk.dev.annotation.DisplayName;
import nuricanozturk.dev.exception.ParameterNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodRunner {

    private final FileReader m_fileReader;
    private Class<?> m_currentClass;

    public MethodRunner(FileReader m_fileReader) {
        this.m_fileReader = m_fileReader;
    }

    public String run(MethodWrapper method, Class<?> $class) {
        m_fileReader.set$class($class);
        m_currentClass = $class;
        var displayName = method.getMethod().getName();
        try {
            var displayNameAnnotation = method.getMethod().getAnnotation(DisplayName.class);

            if (displayNameAnnotation != null) {
                var value = displayNameAnnotation.value();
                displayName = value.isBlank() || value.isEmpty() ? displayName : value;
            }

            var constructor = $class.getDeclaredConstructor().newInstance();

            if (method.isParameterizedTest())
                runParameterizedTest(method, constructor);

            else runUnitTest(method, constructor);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return displayName + " named method was runned!\n";
    }

    private void runUnitTest(MethodWrapper method, Object constructor) {
        var realMethod = method.getMethod();
        realMethod.setAccessible(true);
        try {
            realMethod.invoke(constructor);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } finally {
            realMethod.setAccessible(false);
        }
    }

    private String getParameters(Method realMethod) {
        var csvFile = realMethod.getAnnotation(CsvFile.class);
        var csvSource = realMethod.getAnnotation(CsvSource.class);

        if (csvFile != null)
            return getCsvFileData(csvFile);

        else if (csvSource != null)
            return getCsvSource(csvSource);


        return "";
    }

    private String getCsvSource(CsvSource csvSource) {
        return csvSource.value();
    }

    private String getCsvFileData(CsvFile csvFile) {
        return m_fileReader.readFileCsvFormat(csvFile);
    }


    private void runParameterizedTest(MethodWrapper method, Object constructor) {
        var realMethod = method.getMethod();
        var csvParameters = getParameters(realMethod).split(",");

        realMethod.setAccessible(true);

        var paramType = realMethod.getParameterTypes()[0];

        if (paramType == null)
            throw new ParameterNotFoundException("Parameter not found on " + realMethod.getName() + " on " + m_currentClass.getSimpleName());

        try {
            for (String source : csvParameters) {
                var parameter = getParameterViaType(source, paramType);
                realMethod.invoke(constructor, parameter);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } finally {
            realMethod.setAccessible(false);
        }
    }

    private Object getParameterViaType(String source, Class<?> paramType) {
        if (paramType.equals(String.class))
            return source;

        else if (paramType.equals(int.class) || paramType.equals(Integer.class))
            return Integer.parseInt(source);

        else if (paramType.equals(double.class) || paramType.equals(Double.class))
            return Double.parseDouble(source);

        else if (paramType.equals(long.class) || paramType.equals(Long.class))
            return Long.parseLong(source);

        else if (paramType.equals(Boolean.class) || paramType.equals(boolean.class))
            return Boolean.parseBoolean(source);
        else
            return source.charAt(0);
    }
}
