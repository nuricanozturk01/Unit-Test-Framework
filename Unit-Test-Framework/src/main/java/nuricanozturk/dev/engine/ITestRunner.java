/*----------------------------------------------------------------
	FILE		: ITestRunner.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	ITestRunner interface represent the test runner operations.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

@FunctionalInterface
interface ITestRunner {
    void run(Class<?> $class);
}
