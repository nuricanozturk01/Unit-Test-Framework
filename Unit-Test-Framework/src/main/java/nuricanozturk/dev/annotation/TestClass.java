/*----------------------------------------------------------------
	FILE		: TestClass.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	TestClass annotation class represent testing class.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TestClass {}
