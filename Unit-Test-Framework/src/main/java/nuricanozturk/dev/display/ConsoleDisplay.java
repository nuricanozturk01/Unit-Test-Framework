package nuricanozturk.dev.display;

public final class ConsoleDisplay implements IDisplayEngine {

    @Override
    public void display(String msg) {
        System.out.println(msg);
    }
    @Override
    public void displayMethod(String msg) {
        System.out.println( Color.CYAN.getColor() + "===> " + msg + " method:");
    }

    @Override
    public void displayClass(String msg) {
        System.out.println("\n" + Color.BLUE.getColor() + "=> " + msg + " class' tests has started!");
    }

    @Override
    public void displayUnitTestSuccess(String displayName) {
        System.out.printf("%s\t[SUCCESS] Method: [%s]%s\n", Color.GREEN.getColor(), displayName, Color.NORMAL_TEXT.getColor());
    }

    @Override
    public void displayUnitTestFail(String displayName, Object expected, Object actual) {
        var failMessage = "expected: " + expected + " actual: " + actual;
        System.out.printf("%s\t[FAIL] [%s] [%s]!%s\n", Color.RED.getColor(), displayName, failMessage, Color.RESET.getColor());
    }

    @Override
    public void displayParameterizedTestFail(String displayName, Object expected, Object actual) {
        var failMessage = "expected: " + expected + " actual: " + actual;
        System.out.printf("%s\t[FAIL] [%s] [%s]%s\n", Color.RED.getColor(), displayName, failMessage, Color.RESET.getColor());
    }

    @Override
    public void displayParameterizedTestSuccess(String displayName, String msg) {
        System.out.printf("%s\t[SUCCESS] Method: [%s] Parameter: [%s]%s\n", Color.GREEN.getColor(), displayName, msg, Color.RESET.getColor());
    }
}
