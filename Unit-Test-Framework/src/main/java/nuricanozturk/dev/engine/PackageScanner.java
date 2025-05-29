/*----------------------------------------------------------------
	FILE		: PackageScanner.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	PackageScanner class scan the packages that find the @TestClass annotations classes.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class PackageScanner implements IPackageScanner {
  @Override
  public List<String> getPackages(final String directory) {
    final var packageNames = new ArrayList<String>();
    try {
      final var folder = new File(directory);
      final var files = folder.listFiles();

      if (files != null) {
        for (final File file : files) {
          if (file.isDirectory()) {
            final var subPackageNames = this.getPackages(file.getPath());
            packageNames.addAll(subPackageNames);
          } else if (file.isFile() && file.getName().endsWith(".java")) {
            final var className = file.getName().replace(".java", "");
            final var packageName = file.getParent().substring(17);
            final var fullClassName = packageName.replace("/", ".") + "." + className;
            packageNames.add(fullClassName);
          }
        }
      }
    } catch (final Exception e) {
      e.printStackTrace();
    }

    return packageNames;
  }

  @Override
  public List<Class<?>> getClasses() {
    final var packages = this.getPackages("src/test_fw");
    final var list = new ArrayList<Class<?>>();

    for (String pkg : packages) {
      try {
        pkg = pkg.replace("/", ".");
        list.add(Class.forName(pkg));
      } catch (final ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
    return list;
  }
}
