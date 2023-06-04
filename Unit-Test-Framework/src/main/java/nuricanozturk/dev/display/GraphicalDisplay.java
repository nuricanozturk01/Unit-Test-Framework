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
    public void display(String msg) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
    }

    @Override
    public void displayMethod(String msg) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
    }

    @Override
    public void displayClass(String msg) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
    }

    @Override
    public void displayUnitTestSuccess(String displayName) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
    }

    @Override
    public void displayUnitTestFail(String displayName, Object expected, Object actual) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
    }

    @Override
    public void displayParameterizedTestFail(String displayName, Object expected, Object actual) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
    }

    @Override
    public void displayParameterizedTestSuccess(String displayName, String msg) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET!");
    }
}
