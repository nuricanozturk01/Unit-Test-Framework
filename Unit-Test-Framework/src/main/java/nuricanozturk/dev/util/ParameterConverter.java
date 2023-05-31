package nuricanozturk.dev.util;

public class ParameterConverter {
    public static Object parseParameterByType(String source, Class<?> paramType) {
        if (paramType.equals(String.class))
            return source;

        else if (paramType.equals(int.class) || paramType.equals(Integer.class))
            return Integer.parseInt(source);

        else if (paramType.equals(double.class) || paramType.equals(Double.class))
            return Double.parseDouble(source);

        else if (paramType.equals(long.class) || paramType.equals(Long.class))
            return Long.parseLong(source);

        else if (paramType.equals(Boolean.class) || paramType.equals(boolean.class))
            return Boolean.parseBoolean(source);
        else
            return source.charAt(0);
    }
}
