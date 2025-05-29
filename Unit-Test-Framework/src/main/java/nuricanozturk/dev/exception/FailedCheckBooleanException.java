/*----------------------------------------------------------------
	FILE		: FailedCheckBooleanException.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	FailedCheckBooleanException exception class for failed boolean tests.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.exception;

public final class FailedCheckBooleanException extends RuntimeException {
  private Object expected;
  private Object actual;

  public FailedCheckBooleanException() {

  }

  public FailedCheckBooleanException(final String message) {
    super(message);
  }

  public <T> FailedCheckBooleanException(final T expected, final T actual) {
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
