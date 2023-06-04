/*----------------------------------------------------------------
	FILE		: IEngine.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	IEngine interface represent the test engine operations.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import java.util.List;

sealed interface IEngine permits TestEngine{

    void startTest();
    List<Class<?>> decomposeHasTestClassAnnotationClasses();
}
