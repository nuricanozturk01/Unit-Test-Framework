package nuricanozturk.dev.exception;

public class FailedCheckEqualException extends RuntimeException {
    private Object expected;
    private Object actual;

    public FailedCheckEqualException(String message) {
        super(message);
    }

    public <T> FailedCheckEqualException(T expected, T actual) {
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
