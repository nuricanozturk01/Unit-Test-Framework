package nuricanozturk.dev.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(CsvFiles.class)
public @interface CsvFile
{
    String value() default "";
}
