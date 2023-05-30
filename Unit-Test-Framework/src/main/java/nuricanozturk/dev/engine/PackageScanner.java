package nuricanozturk.dev.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PackageScanner {


    private static List<String> getPackages(String directory) {
        var packageNames = new ArrayList<String>();

        try {
            var folder = new File(directory);
            var files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        var subPackageNames = getPackages(file.getPath());
                        packageNames.addAll(subPackageNames);
                    }
                    else if (file.isFile() && file.getName().endsWith(".java")) {
                        var className = file.getName().replace(".java", "");
                        var packageName = file.getParent().substring(17);
                        var fullClassName = packageName.replace("/", ".") + "." + className;
                        packageNames.add(fullClassName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return packageNames;
    }

    public static List<Class<?>> getClasses() {
        var packages = getPackages("src/test_fw");
        var list = new ArrayList<Class<?>>();

        for (String pkg : packages) {
            try {
                pkg = pkg.replace("/", ".");
                list.add(Class.forName(pkg));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
