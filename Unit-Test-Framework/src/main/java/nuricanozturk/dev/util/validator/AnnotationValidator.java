/*----------------------------------------------------------------
	FILE		: AnnotationValidator.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	AnnotationValidator class validate annotations that exists or not.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.util.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class AnnotationValidator {
  private AnnotationValidator() {

  }

  public static boolean validateAnnotation(
      final Method method, final Class<? extends Annotation> annotation) {
    return method.getAnnotation(annotation) != null;
  }

  public static boolean validateAnnotation(
      final Class<?> cls, final Class<? extends Annotation> annotation) {
    return cls.getAnnotation(annotation) != null;
  }
}
