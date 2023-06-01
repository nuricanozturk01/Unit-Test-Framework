package nuricanozturk.dev.display;

public enum Color {
    NORMAL_TEXT("\u001B[0m"), BLACK("\u001B[30m"), YELLOW("\u001B[33m"), RED("\u001B[31m"), GREEN("\u001B[32m"),
    WHITE("\u001B[37m"), BLUE("\u001B[34m"), CYAN("\u001B[36m"), PURPLE("\u001B[35m"), RESET("\u001B[0;1m");

    private final String m_color;

    Color(String color) {
        m_color = color;
    }

    public String getColor() {
        return m_color;
    }
}
