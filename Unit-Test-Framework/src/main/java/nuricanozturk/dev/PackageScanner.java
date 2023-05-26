package nuricanozturk.dev;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PackageScanner {
    public static void main(String[] args) {
        List<String> packageNames = getPackages("src/test");

        System.out.println("Paketler ve Sınıflar:");
        for (String packageName : packageNames) {
            System.out.println(packageName);
        }
    }

    public static List<String> getPackages(String directory) {
        var packageNames = new ArrayList<String>();

        try {
            File folder = new File(directory);
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        String packageName = file.getPath().replace("src" + File.separator, "").replace(File.separator, ".");
                        packageNames.add(packageName);

                        List<String> subPackageNames = getPackages(file.getPath());
                        packageNames.addAll(subPackageNames);
                    } else if (file.isFile() && file.getName().endsWith(".java")) {
                        String className = file.getName().replace(".java", "");
                        String packageName = file.getParent().replace("src" + File.separator, "").replace(File.separator, ".");
                        String fullClassName = packageName + "." + className;
                        packageNames.add(fullClassName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return packageNames;
    }
}
