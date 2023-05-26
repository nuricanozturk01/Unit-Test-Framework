package nuricanozturk.dev;

import nuricanozturk.dev.annotation.CsvSource;
import nuricanozturk.dev.annotation.CsvSources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main
{


    public static void main(String[] args)
    {
        List<String> packageNames = getSubPackages("src.unit_test");

        System.out.println("Paket içindeki alt paketler:");
        for (String packageName : packageNames)
        {
            System.out.println(packageName);
        }
    }

    @CsvSources({
            @CsvSource("a.txt"),
            @CsvSource("b.txt")
    })
    public static List<String> getSubPackages(String packageName)
    {
        List<String> packageNames = new ArrayList<>();

        try
        {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            java.net.URL resource = classLoader.getResource(path);

            if (resource != null)
            {
                File directory = new File(resource.getFile());
                File[] files = directory.listFiles();

                for (File file : files)
                {
                    if (file.isDirectory())
                    {
                        String subPackageName = packageName + "." + file.getName();
                        packageNames.add(subPackageName);
                    }
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return packageNames;
    }

}