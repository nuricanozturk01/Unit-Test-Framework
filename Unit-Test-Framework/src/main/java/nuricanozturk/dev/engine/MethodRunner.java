package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.CsvFile;
import nuricanozturk.dev.annotation.CsvSource;
import nuricanozturk.dev.annotation.DisplayName;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class MethodRunner {

    private final FieldParser m_fieldParser;

    public MethodRunner(FieldParser m_fieldParser) {
        this.m_fieldParser = m_fieldParser;
    }

    public String run(MethodWrapper method, Class<?> $class) {
        m_fieldParser.set$class($class);
        var displayName = "";
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

        var paramType = realMethod.getParameterTypes()[0];

        if (paramType == null)
            return "";

        if (csvFile != null)
            return getCsvFileDatas(csvFile, paramType);

        else if (csvSource != null)
            return getCsvSource(csvSource, paramType);


        return "";
    }

    private String getCsvSource(CsvSource csvSource, Class<?> paramType) {
        return csvSource.value();
    }

    private String getCsvFileDatas(CsvFile csvFile, Class<?> paramType) {
        var path = csvFile.value();
        var filePath = Path.of(path);
        var sb = new StringBuilder();
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.map(line -> line.split("\\s+"))
                    .map(words -> String.join(",", words))
                    .forEach(sb::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    private void runParameterizedTest(MethodWrapper method, Object constructor) {
        var realMethod = method.getMethod();
        var csvParameters = getParameters(realMethod).split(",");
        realMethod.setAccessible(true);
        var paramType = realMethod.getParameterTypes()[0];

        if (paramType == null)
            return;
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
