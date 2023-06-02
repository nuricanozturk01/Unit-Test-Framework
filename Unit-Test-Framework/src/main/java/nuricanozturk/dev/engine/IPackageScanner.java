/*----------------------------------------------------------------
	FILE		: IPackageScanner.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	IPackageScanner interface represent the package scanner operations.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import java.util.List;

public interface IPackageScanner {
    List<Class<?>> getClasses();
    List<String> getPackages(String directory);
}
