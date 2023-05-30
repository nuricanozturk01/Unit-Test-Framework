package nuricanozturk.dev.display;

public class ConsoleDisplay implements IDisplayEngine {
    @Override
    public void display(String msg) {
        System.out.println(msg);
    }
}
