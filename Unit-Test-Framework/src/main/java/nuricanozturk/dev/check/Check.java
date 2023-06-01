package nuricanozturk.dev.check;

import nuricanozturk.dev.exception.FailedCheckArrayEqualException;
import nuricanozturk.dev.exception.FailedCheckBooleanException;
import nuricanozturk.dev.exception.FailedCheckEqualException;

import java.util.Arrays;
import java.util.Collection;

public final class Check {
    private static final double DEFAULT_DELTA = .0014;

    private Check() {
    }

    public static void checkEqual(byte expected, byte actual) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkEqual(int expected, int actual) {
        if (expected != actual)
            throw new FailedCheckEqualException(expected, actual);
    }

    public static void checkEqual(double expected, double actual, double delta) {
        if (expected != actual)
            throw new FailedCheckEqualException(expected, actual);
    }

    public static void checkEqual(short expected, short actual) {
        if (expected != actual)
            throw new FailedCheckEqualException(expected, actual);
    }

    public static void checkEqual(float expected, float actual, float delta) {
        if (expected != actual)
            throw new FailedCheckEqualException(expected, actual);
    }

    public static void checkEqual(Object expected, Object actual) {
        if (!expected.equals(actual))
            throw new FailedCheckEqualException(expected, actual);
    }

    //-----------------------------------------------------------------------------------------------------------------
    public static void checkTrue(boolean exp) {

        if (!exp)
            throw new FailedCheckBooleanException(true, false);
    }

    public static void checkFalse(boolean exp) {
        if (exp)
            throw new FailedCheckBooleanException(false, true);
    }

    //-----------------------------------------------------------------------------------------------------------------
    public static void checkNotEqual(Object expected, Object actual) {
        if (expected.equals(actual))
            throw new FailedCheckEqualException("different things", actual);
    }

    public static void checkNotEqual(int expected, int actual) {
        if (expected == actual)
            throw new FailedCheckEqualException(expected, actual);
    }

    public static void checkNotEqual(float expected, float actual, float delta) {
        if (expected == actual)
            throw new FailedCheckEqualException(expected, actual);
    }

    public static void checkNotEqual(double expected, double actual, double delta) {
        if (expected == actual)
            throw new FailedCheckEqualException(expected, actual);
    }

    public static void checkNotEqual(short expected, short actual) {
        if (expected == actual)
            throw new FailedCheckEqualException(expected, actual);
    }

    public static void checkNotEqual(byte expected, byte actual) {
        if (expected == actual)
            throw new FailedCheckEqualException(expected, actual);
    }

    //------------------------------------------------------------------------------------------------------------------

    public static void checkArrayEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (!Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }

    public static void checkArrayEqual(byte[] arr1, byte[] arr2) {
        if (arr1.length != arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (!Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }

    public static void checkArrayEqual(short[] arr1, short[] arr2) {
        if (arr1.length != arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (!Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }

    public static void checkArrayEqual(double[] arr1, double[] arr2) {
        if (arr1.length != arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (!Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }

    public static void checkArrayEqual(float[] arr1, float[] arr2) {
        if (arr1.length != arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (!Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }

    public static void checkArrayEqual(Object[] arr1, Object[] arr2) {
        if (arr1.length != arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (!Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }
    //------------------------------------------------------------------------------------------------------------------

    public static void checkArrayNotEqual(int[] arr1, int[] arr2) {
        if (arr1.length == arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }

    public static void checkArrayNotEqual(byte[] arr1, byte[] arr2) {
        if (arr1.length == arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }

    public static void checkArrayNotEqual(short[] arr1, short[] arr2) {
        if (arr1.length == arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }

    public static void checkArrayNotEqual(double[] arr1, double[] arr2) {
        if (arr1.length == arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }

    public static void checkArrayNotEqual(float[] arr1, float[] arr2) {
        if (arr1.length == arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }

    public static void checkArrayNotEqual(Object[] arr1, Object[] arr2) {
        if (arr1.length == arr2.length)
            throw new FailedCheckEqualException(arr1.length, arr2.length);

        if (Arrays.equals(arr1, arr2))
            throw new FailedCheckArrayEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }


    //------------------------------------------------------------------------------------------------------------------

    public static <T> void checkCollectionEqual(Collection<T> collection1, Collection<T> collection2) {
        if (collection1.size() != collection2.size())
            throw new FailedCheckEqualException(collection1.size(), collection2.size());
        var list1 = collection1.stream().toList();
        var list2 = collection2.stream().toList();

        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i)))
                throw new FailedCheckArrayEqualException(Arrays.toString(list1.toArray()), Arrays.toString(list2.toArray()));
        }
    }

    public static <T> void checkCollectionNotEqual(Collection<T> collection1, Collection<T> collection2) {

        if (collection1.size() == collection2.size())
            throw new FailedCheckEqualException(collection1.size(), collection2.size());
        var list1 = collection1.stream().toList();
        var list2 = collection2.stream().toList();

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).equals(list2.get(i)))
                throw new FailedCheckArrayEqualException(Arrays.toString(list1.toArray()), Arrays.toString(list2.toArray()));
        }
    }

}
