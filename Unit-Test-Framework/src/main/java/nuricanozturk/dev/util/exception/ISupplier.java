/*----------------------------------------------------------------
	FILE		: ISupplier.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	ISupplier interface represent the Supplier but eliminate the catch exceptions.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.util.exception;

@FunctionalInterface
public interface ISupplier<T> {
  T get() throws Exception;
}
