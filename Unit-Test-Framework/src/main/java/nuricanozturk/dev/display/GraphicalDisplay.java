/*----------------------------------------------------------------
	FILE		: GraphicalDisplay.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	GraphicalDisplay class represent display engine.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.display;

import nuricanozturk.dev.display.gui.MethodTestWrapper;
import nuricanozturk.dev.display.gui.TestGui;
import nuricanozturk.dev.display.gui.TestWrapper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public final class GraphicalDisplay implements IDisplayEngine
{

    private final List<TestWrapper> m_TestWrappers;
    private TestWrapper m_testWrapper;
    private MethodTestWrapper m_methodTestWrapper;

    public GraphicalDisplay()
    {
        m_TestWrappers = new ArrayList<>();
    }

    @Override
    public void display(String msg)
    {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
    }

    @Override
    public void displayMethod(String msg)
    {
        m_methodTestWrapper = new MethodTestWrapper(msg);
    }

    @Override
    public void displayClass(String msg)
    {
        m_testWrapper = new TestWrapper(msg);
    }

    @Override
    public void displayUnitTestSuccess(String displayName)
    {
        m_methodTestWrapper.addTestResultStr(format("\t[SUCCESS] Method: [%s]\n", displayName));
    }

    @Override
    public void displayUnitTestFail(String displayName, Object expected, Object actual)
    {
        var failMessage = "expected: " + expected + " actual: " + actual;

        m_methodTestWrapper.addTestResultStr(format("\t[FAIL] [%s] [%s]!\n", displayName, failMessage));
    }

    @Override
    public void displayParameterizedTestFail(String displayName, Object expected, Object actual)
    {
        var failMessage = "expected: " + expected + " actual: " + actual;
        m_methodTestWrapper.addTestResultStr(format("\t[FAIL] [%s] [%s]!\n", displayName, failMessage));
    }

    @Override
    public void displayParameterizedTestSuccess(String displayName, String msg)
    {
        m_methodTestWrapper.addTestResultStr(
                format("\t[SUCCESS] Method: [%s] Parameter: [%s]\n", displayName, msg));
    }

    public void finishTest()
    {
        var m_testGui = new TestGui(m_TestWrappers);
        m_testGui.start();
    }

    public void finishMethodTest()
    {
        m_testWrapper.addMethod(m_methodTestWrapper);
    }

    public void finishClassTest()
    {
        m_TestWrappers.add(m_testWrapper);
    }
}
