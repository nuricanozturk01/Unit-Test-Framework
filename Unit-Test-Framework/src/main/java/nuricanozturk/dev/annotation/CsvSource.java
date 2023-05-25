package nuricanozturk.dev.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(CsvSources.class)
public @interface CsvSource
{
    String value() default "";
}
