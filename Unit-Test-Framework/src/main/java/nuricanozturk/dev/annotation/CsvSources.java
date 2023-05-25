package nuricanozturk.dev.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CsvSources
{
    CsvSource [] value();
}