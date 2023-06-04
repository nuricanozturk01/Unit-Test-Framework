/*----------------------------------------------------------------
	FILE		: IDisplayEngine.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	IDisplayEngine interface represent display engine methods.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.display;


public sealed interface IDisplayEngine permits ConsoleDisplay, GraphicalDisplay{

    void display(String msg);
    void displayMethod(String msg);
    void displayClass(String msg);

    void displayUnitTestSuccess(String displayName);

    void displayUnitTestFail(String displayName, Object expected, Object actual);

    void displayParameterizedTestFail(String displayName, Object expected, Object actual);

    void displayParameterizedTestSuccess(String displayName, String msg);
}
