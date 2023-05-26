package nuricanozturk.dev;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class AnnotationScanner
{
    public static List<Class<?>> getAnnotatedClasses(String packageName, Class<? extends Annotation> annotation)
    {
        return null;
    }

    private static List<Class<?>> findClassesInPackage(String packageName, String packagePath)
            throws ClassNotFoundException
    {
        List<Class<?>> classes = new ArrayList<>();
        String[] classFiles = new java.io.File(packagePath).list();

        for (String classFile : classFiles)
        {
            if (classFile.endsWith(".class"))
            {
                String className = packageName + '.' + classFile.substring(0, classFile.length() - 6);
                Class<?> cls = Class.forName(className);
                classes.add(cls);
            }
        }

        return classes;
    }

    private static boolean hasAnnotation(Class<?> cls, Class<? extends Annotation> annotation)
    {
        return cls.isAnnotationPresent(annotation);
    }
}
