/*----------------------------------------------------------------
	FILE		: IMethodRunner.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	IMethodRunner interface represent the method runner operations.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

@FunctionalInterface
interface IMethodRunner
{
    void run(MethodWrapper method, Class<?> $class, Object ctor);
}
