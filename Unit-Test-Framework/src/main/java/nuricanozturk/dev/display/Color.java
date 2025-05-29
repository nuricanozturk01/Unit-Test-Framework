/*----------------------------------------------------------------
	FILE		: Color.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	Color enum class represent colors for display engine
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.display;

public enum Color {
  NORMAL_TEXT("\u001B[0m"),
  BLACK("\u001B[30m"),
  YELLOW("\u001B[33m"),
  RED("\u001B[31m"),
  GREEN("\u001B[32m"),
  WHITE("\u001B[37m"),
  BLUE("\u001B[34m"),
  CYAN("\u001B[36m"),
  PURPLE("\u001B[35m"),
  RESET("\u001B[0;1m");

  private final String color;

  Color(final String color) {
    this.color = color;
  }

  public String getColor() {
    return this.color;
  }
}
