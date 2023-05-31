package nuricanozturk.dev.display;


import java.util.concurrent.atomic.AtomicReference;

public interface IDisplayEngine {
    AtomicReference<IDisplayEngine> DISPLAY_ENGINE = new AtomicReference<>();

    static IDisplayEngine getInstance(DisplayType displayType) {
        if (DISPLAY_ENGINE.get() == null)
            DISPLAY_ENGINE.set(DisplayEngineFactory.createDisplay(displayType));
        return DISPLAY_ENGINE.get();
    }

    static IDisplayEngine getInstance() {
        return DISPLAY_ENGINE.get();
    }

    void displaySuccess(String msg);

    void displayFail(String msg);
}
