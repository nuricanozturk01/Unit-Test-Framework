package nuricanozturk.dev.engine;

import java.util.ArrayList;
import java.util.List;

import static nuricanozturk.dev.engine.PackageScanner.getClasses;

public final class TestEngine
{
    private List<Class<?>> m_testClasses;

    public TestEngine()
    {
        m_testClasses = getClasses();

    }

    private List<Class<?>> getTesClassAnnotatedClasses()
    {
        return null;
    }

    private void insertTestClasses()
    {

    }
}
