/*----------------------------------------------------------------
	FILE		: TestClassNotFoundException.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	TestClassNotFoundException exception class thrown when not found @TestClass.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.exception;

public final class TestClassNotFoundException extends RuntimeException {
  public TestClassNotFoundException(final String message) {
    super(message);
  }
}
