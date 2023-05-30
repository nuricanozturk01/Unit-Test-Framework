package nuricanozturk.dev.engine;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PackageScanner {


    public static void main(String[] args) {
        var packageNames = getPackages("src/my_test");

        /*Class<?> c = null;
        try {
            c = Class.forName(".ozturk.B");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(c.getName());*/
        //getClasses();
        /*System.out.println("Paketler ve Sınıflar:");
        for (var p : packageNames)
            System.out.println(p);*/
    }

    public static List<String> getPackages(String directory) {
        var packageNames = new ArrayList<String>();

        try {
            File folder = new File(directory);
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        var subPackageNames = getPackages(file.getPath());
                        packageNames.addAll(subPackageNames);
                    } else if (file.isFile() && file.getName().endsWith(".java")) {
                        String className = file.getName().replace(".java","");

                        String packageName = file.getParent().substring(17);
                        String fullClassName = packageName.replace("/",".") + "." + className;

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
        var packages = getPackages("src/my_test");
        var list = new ArrayList<Class<?>>();

        for (String aPackage : packages) {
            try {
                aPackage = aPackage.replace("/",".");

                var c = Class.forName(aPackage);
                System.out.println("AAAAA:" + c.getName());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return list;
    }
}
