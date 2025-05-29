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

  public FailedCheckEqualException(final String message) {
    super(message);
  }

  public <T> FailedCheckEqualException(final T expected, final T actual) {
    this.expected = expected;
    this.actual = actual;
  }

  public Object getExpected() {
    return this.expected;
  }

  public Object getActual() {
    return this.actual;
  }
}
