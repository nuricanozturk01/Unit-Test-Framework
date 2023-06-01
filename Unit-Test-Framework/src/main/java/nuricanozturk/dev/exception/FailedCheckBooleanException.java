package nuricanozturk.dev.exception;

public class FailedCheckBooleanException extends RuntimeException {
    private Object expected;
    private Object actual;

    public FailedCheckBooleanException() {

    }
    public FailedCheckBooleanException(String message) {
        super(message);
    }

    public <T> FailedCheckBooleanException(T expected, T actual) {
        this.expected = expected;
        this.actual = actual;
    }

    public Object getExpected() {
        return expected;
    }

    public Object getActual() {
        return actual;
    }
}
