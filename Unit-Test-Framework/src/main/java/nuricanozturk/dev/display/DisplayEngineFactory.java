package nuricanozturk.dev.display;

public final class DisplayEngineFactory {

    public static IDisplayEngine createDisplay(DisplayType displayType) {
        return switch (displayType) {
            //case GUI -> new GraphicalDisplay();
            default -> new ConsoleDisplay();
        };
    }
}
