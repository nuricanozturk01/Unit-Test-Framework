/*----------------------------------------------------------------
	FILE		: GraphicalDisplay.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	GraphicalDisplay class represent display engine.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.display;

public final class GraphicalDisplay implements IDisplayEngine {
  public GraphicalDisplay() {
    throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
  }

  @Override
  public void display(final String msg) {
    throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
  }

  @Override
  public void displayMethod(final String msg) {
    throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
  }

  @Override
  public void displayClass(final String msg) {
    throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
  }

  @Override
  public void displayUnitTestSuccess(final String displayName) {
    throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
  }

  @Override
  public void displayUnitTestFail(
      final String displayName, final Object expected, final Object actual) {
    throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
  }

  @Override
  public void displayParameterizedTestFail(
      final String displayName, final Object expected, final Object actual) {
    throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
  }

  @Override
  public void displayParameterizedTestSuccess(final String displayName, final String msg) {
    throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
  }
}
