package nuricanozturk.dev.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FailedCheckArrayEqualException extends RuntimeException {
    private Object[] expected;
    private Object[] actual;

    private String expectedStr;
    private String actualStr;

    public FailedCheckArrayEqualException(String message) {
        super(message);
    }

    public FailedCheckArrayEqualException(Object[] expected, Object[] actual) {
        this.expected = expected;
        this.actual = actual;
    }

    public FailedCheckArrayEqualException(String expected, String actual) {
        expectedStr = expected;
        actualStr = actual;
    }



    private String getArrayStr(Object[] arr) {
        return Arrays.stream(arr).map(String::valueOf).collect(Collectors.joining(","));
    }

    public String getExpectedString() {
        return getArrayStr(expected);
    }

    public String getActualString() {
        return getArrayStr(actual);
    }

    public Object[] getExpected() {
        return expected;
    }

    public Object[] getActual() {
        return actual;
    }
}
