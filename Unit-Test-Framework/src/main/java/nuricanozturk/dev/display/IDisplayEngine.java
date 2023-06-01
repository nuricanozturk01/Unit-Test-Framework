package nuricanozturk.dev.display;


public interface IDisplayEngine {

    void display(String msg);
    void displayMethod(String msg);
    void displayClass(String msg);

    void displayUnitTestSuccess(String displayName);

    void displayUnitTestFail(String displayName, Object expected, Object actual);

    void displayParameterizedTestFail(String displayName, Object expected, Object actual);

    void displayParameterizedTestSuccess(String displayName, String msg);
}
