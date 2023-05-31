package nuricanozturk.dev.check;

import nuricanozturk.dev.display.IDisplayEngine;
import nuricanozturk.dev.exception.FailedTestException;

import java.util.Collection;

public final class Check {

    public static void checkEqual(byte expected, byte actual) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkEqual(int expected, int actual) {
        try {
            if (expected != actual)
                throw new FailedTestException("NOT SUCCESS TEST");
            IDisplayEngine.getInstance().displaySuccess("SUCCESS TEST!");
        } catch (FailedTestException ex) {
            IDisplayEngine.getInstance().displayFail(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static void checkEqual(boolean expected, boolean actual) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkEqual(double expected, double actual, double delta) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkEqual(short expected, short actual) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkEqual(float expected, float actual, float delta) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkEqual(Object expected, Object actual) {
        throw new UnsupportedOperationException("TODO: ");
    }

    //-----------------------------------------------------------------------------------------------------------------
    public static void checkTrue(boolean exp, String failMessage) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkFalse(boolean exp, String failMessage) {
        throw new UnsupportedOperationException("TODO: ");
    }

    //-----------------------------------------------------------------------------------------------------------------
    public static void checkNotEqual(Object expected, Object actual) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkNotEqual(int expected, int actual) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkNotEqual(float expected, float actual, float delta) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkNotEqual(double expected, double actual, double delta) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkNotEqual(short expected, short actual) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkNotEqual(byte expected, byte actual) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkNotEqual(boolean expected, boolean actual) {
        throw new UnsupportedOperationException("TODO: ");
    }

    //------------------------------------------------------------------------------------------------------------------
    public static void checkArrayEqual(int[] arr1, int[] arr2) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkArrayEqual(byte[] arr1, byte[] arr2) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkArrayEqual(short[] arr1, short[] arr2) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkArrayEqual(double[] arr1, double[] arr2) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static void checkArrayEqual(float[] arr1, float[] arr2) {
        throw new UnsupportedOperationException("TODO: ");
    }

    public static <T> void checkArrayEqual(T[] arr1, T[] arr2) {
        throw new UnsupportedOperationException("TODO: ");
    }
    //------------------------------------------------------------------------------------------------------------------

    public static <T> void checkCollectionEqual(Collection<T> collection1, Collection<T> collection2) {
        throw new UnsupportedOperationException("TODO: ");
    }

}
