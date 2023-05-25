package nuricanozturk.dev;

import nuricanozturk.dev.annotation.TestClass;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class AnnotationScanner
{
    public static List<Class<?>> getAnnotatedClasses(String packageName, Class<? extends Annotation> annotation)
            throws ClassNotFoundException
    {
        List<Class<?>> annotatedClasses = new ArrayList<>();
        Package[] packages = Package.getPackages();

        for (var pkg : packages)
        {

            String packagePath = pkg.getName().replace('.', '/');
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String classPath = classLoader.getResource(packagePath).getPath();

            List<Class<?>> classes = findClassesInPackage(packageName, classPath);

            for (Class<?> cls : classes)
            {
                if (hasAnnotation(cls, annotation))
                { String packagePath = pkg.getName().replace('.', '/');
                    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                    String classPath = classLoader.getResource(packagePath).getPath();

                    List<Class<?>> classes = findClassesInPackage(packageName, classPath);

                    for (Class<?> cls : classes) {
                        if (hasAnnotation(cls, annotation)) {
                            annotatedClasses.add(cls);
                        }
                    }
                    annotatedClasses.add(cls);
                }
            }
        }

        return annotatedClasses;
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

    public static void main(String[] args) throws ClassNotFoundException
    {
        List<Class<?>> annotatedClasses = getAnnotatedClasses("nuricanozturk.dev", TestClass.class);

        for (Class<?> cls : annotatedClasses)
        {
            System.out.println("Annotated class: " + cls.getName());
        }
    }
}
