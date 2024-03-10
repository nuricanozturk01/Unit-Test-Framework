/*----------------------------------------------------------------
	FILE		: MethodRunner.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 05.05.2023
	MethodRunner class  run the method according to test types.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.CsvFile;
import nuricanozturk.dev.annotation.CsvSource;
import nuricanozturk.dev.annotation.DisplayName;
import nuricanozturk.dev.display.IDisplayEngine;
import nuricanozturk.dev.exception.FailedCheckBooleanException;
import nuricanozturk.dev.exception.FailedCheckEqualException;
import nuricanozturk.dev.exception.SourceNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static nuricanozturk.dev.util.ParameterConverter.parseParameterByType;

final class MethodRunner implements IMethodRunner
{

    private final FileReader m_fileReader;
    private final IDisplayEngine m_displayEngine;
    private Class<?> m_currentClass;

    public MethodRunner(FileReader m_fileReader, IDisplayEngine displayEngine)
    {
        this.m_fileReader = m_fileReader;
        m_displayEngine = displayEngine;
    }

    @Override
    public void run(MethodWrapper method, Class<?> $class, Object ctor)
    {
        m_currentClass = $class;

        var displayName = method.getMethod().getName();
        var displayNameAnnotation = method.getMethod().getAnnotation(DisplayName.class);

        if (displayNameAnnotation != null)
        {
            var value = displayNameAnnotation.value();
            displayName = value.isBlank() || value.isEmpty() ? displayName : value;
        }

        m_displayEngine.displayMethod(displayName);

        if (method.isParameterizedTest())
            runParameterizedTest(method, ctor, displayName);

        else if (method.isUnitTest())
            runUnitTest(method, ctor, displayName);

        else runMethod(method, ctor, displayName);
    }

    private void runMethod(MethodWrapper method, Object ctor, String displayName)
    {
        var realMethod = method.getMethod();
        realMethod.setAccessible(true);
        try
        {
            realMethod.invoke(ctor);
            m_displayEngine.displayUnitTestSuccess(displayName);
        } catch (InvocationTargetException e)
        {
            m_displayEngine.displayUnitTestFail(displayName, "", "");
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException("METHOD ERROR!", e);
        } finally
        {
            realMethod.setAccessible(false);
        }
    }

    private void giveMessageParameterizedTest(Throwable cause, String displayName)
    {
        if (cause instanceof FailedCheckBooleanException exception) // Pattern matching since Java 17
            m_displayEngine.displayParameterizedTestFail(displayName, exception.getActual(), exception.getExpected());

        else if (cause instanceof FailedCheckEqualException exception)
            m_displayEngine.displayParameterizedTestFail(displayName, exception.getExpected(), exception.getActual());

        else throw new RuntimeException(
                    "Error on " + displayName + " method! in " + m_currentClass.getSimpleName() + " on parameterized test ",
                    cause);

    }

    private void giveMessageUnitTest(Throwable cause, String displayName)
    {
        if (cause instanceof FailedCheckBooleanException exception)
            m_displayEngine.displayUnitTestFail(displayName, exception.getExpected(), exception.getActual());

        else if (cause instanceof FailedCheckEqualException exception)
            m_displayEngine.displayUnitTestFail(displayName, exception.getExpected(), exception.getActual());

        else throw new RuntimeException(
                    "Error on " + displayName + " method! in " + m_currentClass.getSimpleName() + " on unit test ",
                    cause);
    }

    private void runUnitTest(MethodWrapper method, Object constructor, String displayName)
    {
        var realMethod = method.getMethod();
        realMethod.setAccessible(true);
        try
        {
            realMethod.invoke(constructor);
            m_displayEngine.displayUnitTestSuccess(displayName);
        } catch (InvocationTargetException e)
        {
            Throwable cause = e.getCause();
            giveMessageUnitTest(cause, displayName);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException("METHOD ERROR!", e);
        } finally
        {
            realMethod.setAccessible(false);
        }
    }

    private void runParameterizedTest(MethodWrapper method, Object constructor, String displayName)
    {
        var realMethod = method.getMethod();
        var csvParameters = getParameters(realMethod).replaceAll("\\s", "").split(",");

        var paramType = realMethod.getParameterTypes()[0];

        Object parameter = null;

        try
        {

            realMethod.setAccessible(true);

            for (var source : csvParameters)
            {
                parameter = parseParameterByType(source, paramType);
                realMethod.invoke(constructor, parameter);
                m_displayEngine.displayParameterizedTestSuccess(displayName, source);
            }
        } catch (InvocationTargetException e)
        {
            Throwable cause = e.getCause();
            giveMessageParameterizedTest(cause, displayName);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException("METHOD ERROR!", e);
        } finally
        {
            realMethod.setAccessible(false);
        }
    }

    private String getParameters(Method realMethod)
    {
        var csvFile = realMethod.getAnnotation(CsvFile.class);
        var csvSource = realMethod.getAnnotation(CsvSource.class);


        if (csvFile != null && !csvFile.value().isEmpty() && !csvFile.value().isBlank())
            return m_fileReader.readFileCsvFormat(csvFile);

        if (csvSource != null && !csvSource.value().isEmpty() && !csvSource.value().isBlank())
            return csvSource.value();

        throw new SourceNotFoundException("Source not found!...");
    }
}