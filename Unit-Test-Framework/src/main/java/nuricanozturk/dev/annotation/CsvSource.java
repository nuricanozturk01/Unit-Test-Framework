/*----------------------------------------------------------------
	FILE		: CsvSource.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	CsvSource annotation class represent CsvSource annotation on JUnit
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CsvSource {
  String value() default "";
}
