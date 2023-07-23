/*----------------------------------------------------------------
	FILE		: DisplayEngineFactory.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	DisplayEngineFactory class represent factory method for create display engine.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.display;

public final class DisplayEngineFactory
{

    private DisplayEngineFactory()
    {

    }

    public static IDisplayEngine createDisplay(DisplayType displayType)
    {
        return switch (displayType)
        {
            case GUI -> new GraphicalDisplay();
            default -> new ConsoleDisplay();
        };
    }
}
