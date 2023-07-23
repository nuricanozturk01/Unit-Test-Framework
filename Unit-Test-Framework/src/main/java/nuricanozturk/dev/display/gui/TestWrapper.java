package nuricanozturk.dev.display.gui;

import java.util.ArrayList;
import java.util.List;

public class TestWrapper
{
    private final String m_class;
    private List<MethodTestWrapper> m_methods;

    public TestWrapper(String className)
    {
        m_class = className;
        m_methods = new ArrayList<>();
    }

    public void addMethod(MethodTestWrapper method)
    {
        m_methods.add(method);
    }

    public String getClassName()
    {
        return m_class;
    }

    public List<MethodTestWrapper> getMethods()
    {
        return m_methods;
    }

    @Override
    public String toString()
    {
        return m_class;
    }
}