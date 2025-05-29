/*----------------------------------------------------------------
	FILE		: TriggerClassNotFoundException.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	TriggerClassNotFoundException exception class thrown when not found @TestFrameworkApplication.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.exception;

public final class TriggerClassNotFoundException extends RuntimeException {
  public TriggerClassNotFoundException(final String message) {
    super(message);
  }
}
