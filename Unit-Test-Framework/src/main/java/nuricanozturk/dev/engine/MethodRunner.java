package nuricanozturk.dev.engine;

public class MethodRunner {

    private final FieldParser m_fieldParser;

    public MethodRunner(FieldParser m_fieldParser) {
        this.m_fieldParser = m_fieldParser;
    }

    public String run(MethodWrapper method, Class<?> $class) {

        return "runned " + method.getMethod().getName() + "\n";
    }
}
