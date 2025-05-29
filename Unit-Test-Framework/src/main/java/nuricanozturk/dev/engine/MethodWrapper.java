/*----------------------------------------------------------------
	FILE		: MethodWrapper.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	MethodWrapper class store the method and its necessary annotations. (for easy-use)
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodWrapper {
  private final Method method;
  private final List<Annotation> annotations;
  private boolean isParameterizedTest;
  private boolean isUnitTest;

  public MethodWrapper(final Method method) {
    this.method = method;
    this.annotations = new ArrayList<>();
    this.isParameterizedTest = false;
    this.isUnitTest = false;
  }

  public boolean isUnitTest() {
    return this.isUnitTest;
  }

  public void setUnitTest(final boolean unitTest) {
    this.isUnitTest = unitTest;
  }

  public List<Annotation> getAnnotations() {
    return this.annotations;
  }

  public Method getMethod() {
    return this.method;
  }

  public void addAnnotation(final Annotation annotation) {
    this.annotations.add(annotation);
  }

  public boolean isParameterizedTest() {
    return this.isParameterizedTest;
  }

  public void setParameterizedTest(final boolean parameterizedTest) {
    this.isParameterizedTest = parameterizedTest;
  }
}
