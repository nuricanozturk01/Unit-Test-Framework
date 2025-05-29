/*----------------------------------------------------------------
	FILE		: ConsoleDisplay.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	ConsoleDisplay class represent display engine.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.display;

public final class ConsoleDisplay implements IDisplayEngine {

  @Override
  public void display(final String msg) {
    System.out.println(msg);
  }

  @Override
  public void displayMethod(final String msg) {
    System.out.println(Color.CYAN.getColor() + "===> " + msg + " method:" + Color.RESET.getColor());
  }

  @Override
  public void displayClass(final String msg) {
    System.out.println("\n" + Color.BLUE.getColor() + "=> " + msg + " class' tests has started!");
  }

  @Override
  public void displayUnitTestSuccess(final String displayName) {
    System.out.printf(
        "%s\t[SUCCESS] Method: [%s]%s\n",
        Color.GREEN.getColor(), displayName, Color.RESET.getColor());
  }

  @Override
  public void displayUnitTestFail(final String displayName, final Object expected, final Object actual) {
    final var failMessage = "expected: " + expected + " actual: " + actual;
    System.out.printf(
        "%s\t[FAIL] [%s] [%s]!%s\n",
        Color.RED.getColor(), displayName, failMessage, Color.RESET.getColor());
  }

  @Override
  public void displayParameterizedTestFail(final String displayName, final Object expected, final Object actual) {
    final var failMessage = "expected: " + expected + " actual: " + actual;
    System.out.printf(
        "%s\t[FAIL] [%s] [%s]!%s\n",
        Color.RED.getColor(), displayName, failMessage, Color.RESET.getColor());
  }

  @Override
  public void displayParameterizedTestSuccess(final String displayName, final String msg) {
    System.out.printf(
        "%s\t[SUCCESS] Method: [%s] Parameter: [%s]%s\n",
        Color.GREEN.getColor(), displayName, msg, Color.RESET.getColor());
  }
}
