package nuricanozturk.dev.display;

public final class ConsoleDisplay implements IDisplayEngine {
    //Output background colour codes
    public static final String normalBackground = "\u001B[0m";
    public static final String blackBackground = "\u001B[40m";
    public static final String redBackground = "\u001B[41m";
    public static final String greenBackground = "\u001B[42m";
    public static final String yellowBackground = "\u001B[43m";
    public static final String blueBackground = "\u001B[44m";
    public static final String purpleBackground = "\u001B[45m";
    public static final String cyanBackground = "\u001B[46m";
    public static final String whiteBackground = "\u001B[47m";

    //Text colour codes
    private static final String NORMAL_TEXT = "\u001B[0m";
    private static final String blackText = "\u001B[30m";
    private static final String yellowText = "\u001B[33m";
    private static final String redText = "\u001B[31m";
    private static final String greenText = "\u001B[32m";
    private static final String whiteText = "\u001B[37m";
    private static final String blueText = "\u001B[34m";
    private static final String cyanText = "\u001B[36m";
    private static final String purpleText = "\u001B[35m";

    private static final String GREEN = "\u001B[32;1m";
    private static final String RESET = "\u001B[0;1m";

    @Override
    public void displaySuccess(String msg) {
        System.out.println(greenText + "[+] " + msg + RESET);
    }
    public String getFailText(String msg) {
        return redText + "[-] " + msg + RESET;
    }
    @Override
    public void displayFail(String msg) {
        System.out.println(redText + "[-] " + msg + RESET);
    }
}
