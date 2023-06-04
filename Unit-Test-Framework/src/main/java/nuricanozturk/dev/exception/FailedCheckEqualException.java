/*----------------------------------------------------------------
	FILE		: FailedCheckEqualException.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	FailedCheckEqualException exception class for failed non-boolean tests.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.exception;

public final class FailedCheckEqualException extends RuntimeException {
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
