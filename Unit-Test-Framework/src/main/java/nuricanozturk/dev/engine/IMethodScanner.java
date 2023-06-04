/*----------------------------------------------------------------
	FILE		: IMethodScanner.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	IMethodScanner interface represent the method scanner operations.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;


sealed interface IMethodScanner permits MethodScanner{
    void prepareMethodsForTest(Class<?> $class);
}
