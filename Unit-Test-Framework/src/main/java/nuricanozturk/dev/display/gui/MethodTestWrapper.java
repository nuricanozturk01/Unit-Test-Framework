package nuricanozturk.dev.display.gui;

import java.util.ArrayList;
import java.util.List;

public class MethodTestWrapper
{
    private final String m_MethodName;
    private final List<String> m_testResult;

    public MethodTestWrapper(String methodName)
    {
        m_MethodName = methodName;
        m_testResult = new ArrayList<String>();
    }

    public void addTestResultStr(String result)
    {
        m_testResult.add(result);
    }

    public String getMethodName()
    {
        return m_MethodName;
    }

    public List<String> getTestResults()
    {
        return m_testResult;
    }
}