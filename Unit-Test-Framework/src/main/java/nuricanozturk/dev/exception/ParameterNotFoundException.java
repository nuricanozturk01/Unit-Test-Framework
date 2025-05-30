/*----------------------------------------------------------------
	FILE		: ParameterNotFoundException.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	ParameterNotFoundException exception class for CsvFile annotation.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.exception;

public final class ParameterNotFoundException extends RuntimeException {
  public ParameterNotFoundException(final String message) {
    super(message);
  }
}
