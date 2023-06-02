/*----------------------------------------------------------------
	FILE		: IRunnable.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	IRunnable interface represent the Runnable but eliminate the catch exceptions.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.util.exception;

@FunctionalInterface
public interface IRunnable
{
    void run() throws Exception;
}