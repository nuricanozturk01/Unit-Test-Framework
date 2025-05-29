/*----------------------------------------------------------------
	FILE		: ParameterConverter.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	ParameterConverter class convert the parameters to specific type. (@CsvFile)
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.util;

public final class ParameterConverter {
  private ParameterConverter() {}

  public static Object parseParameterByType(final String source, final Class<?> paramType) {
    if (paramType.equals(String.class)) {
      return source;
    } else if (paramType.equals(int.class) || paramType.equals(Integer.class)) {
      return Integer.parseInt(source);
    } else if (paramType.equals(double.class) || paramType.equals(Double.class)) {
      return Double.parseDouble(source);
    } else if (paramType.equals(long.class) || paramType.equals(Long.class)) {
      return Long.parseLong(source);
    } else if (paramType.equals(Boolean.class) || paramType.equals(boolean.class)) {
      return Boolean.parseBoolean(source);
    } else {
      return source.charAt(0);
    }
  }
}
