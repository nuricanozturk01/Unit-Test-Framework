/*----------------------------------------------------------------
	FILE		: Check.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	Check class represent the Assertions class on JUnit
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.check;

import java.util.Arrays;
import java.util.Collection;
import nuricanozturk.dev.exception.FailedCheckBooleanException;
import nuricanozturk.dev.exception.FailedCheckEqualException;

public final class Check {
  private static final double DEFAULT_DELTA = .0014;

  private Check() {

  }

  public static void checkEqual(final byte expected, final byte actual) {
    if (expected != actual) {
      throw new FailedCheckEqualException(expected, actual);
    }
  }

  public static void checkEqual(final int expected, final int actual) {
    if (expected != actual) {
      throw new FailedCheckEqualException(expected, actual);
    }
  }

  public static void checkEqual(final double expected, final double actual, final double delta) {
    if (Math.abs(expected - actual) > delta) {
      throw new FailedCheckEqualException(expected, actual);
    }
  }

  public static void checkEqual(final short expected, final short actual) {
    if (expected != actual) {
      throw new FailedCheckEqualException(expected, actual);
    }
  }

  public static void checkEqual(final float expected, final float actual, final float delta) {
    if (Math.abs(expected - actual) > delta) {
      throw new FailedCheckEqualException(expected, actual);
    }
  }

  public static void checkEqual(final Object expected, final Object actual) {
    if (!expected.equals(actual)) {
      throw new FailedCheckEqualException(expected, actual);
    }
  }

  // -----------------------------------------------------------------------------------------------------------------
  public static void checkTrue(final boolean exp) {
    if (!exp) {
      throw new FailedCheckBooleanException(true, false);
    }
  }

  public static void checkFalse(final boolean exp) {
    if (exp) {
      throw new FailedCheckBooleanException(false, true);
    }
  }

  // -----------------------------------------------------------------------------------------------------------------
  public static void checkNotEqual(final Object expected, final Object actual) {
    if (expected.equals(actual)) {
      throw new FailedCheckEqualException("different things", actual);
    }
  }

  public static void checkNotEqual(final int expected, final int actual) {
    if (expected == actual) {
      throw new FailedCheckEqualException(expected, actual);
    }
  }

  public static void checkNotEqual(final float expected, final float actual, final float delta) {
    if (Math.abs(expected - actual) <= delta) {
      throw new FailedCheckEqualException(expected, actual);
    }
  }

  public static void checkNotEqual(final double expected, final double actual, final double delta) {
    if (Math.abs(expected - actual) <= delta) {
      throw new FailedCheckEqualException(expected, actual);
    }
  }

  public static void checkNotEqual(final short expected, final short actual) {
    if (expected == actual) {
      throw new FailedCheckEqualException(expected, actual);
    }
  }

  public static void checkNotEqual(final byte expected, final byte actual) {
    if (expected == actual) {
      throw new FailedCheckEqualException(expected, actual);
    }
  }

  // ------------------------------------------------------------------------------------------------------------------

  public static void checkArrayEqual(final int[] arr1, final int[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length != arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    if (!Arrays.equals(arr1, arr2)) {
      throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }
  }

  public static void checkArrayEqual(final byte[] arr1, final byte[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length != arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    if (!Arrays.equals(arr1, arr2)) {
      throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }
  }

  public static void checkArrayEqual(final short[] arr1, final short[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length != arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    if (!Arrays.equals(arr1, arr2)) {
      throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }
  }

  public static void checkArrayEqual(final double[] arr1, final double[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length != arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    for (int i = 0; i < arr1.length; i++) {
      if (Math.abs(arr1[i] - arr2[i]) > DEFAULT_DELTA) {
        throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
      }
    }
  }

  public static void checkArrayEqual(final float[] arr1, final float[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length != arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    for (int i = 0; i < arr1.length; i++) {
      if (Math.abs(arr1[i] - arr2[i]) > DEFAULT_DELTA) {
        throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
      }
    }
  }

  public static void checkArrayEqual(final Object[] arr1, final Object[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length != arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    if (!Arrays.equals(arr1, arr2)) {
      throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }
  }

  // ------------------------------------------------------------------------------------------------------------------

  public static void checkArrayNotEqual(final int[] arr1, final int[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length == arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    if (Arrays.equals(arr1, arr2)) {
      throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }
  }

  public static void checkArrayNotEqual(final byte[] arr1, final byte[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length == arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    if (Arrays.equals(arr1, arr2)) {
      throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }
  }

  public static void checkArrayNotEqual(final short[] arr1, final short[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length == arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    if (Arrays.equals(arr1, arr2)) {
      throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }
  }

  public static void checkArrayNotEqual(final double[] arr1, final double[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length == arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    for (int i = 0; i < arr1.length; i++) {
      if (Math.abs(arr1[i] - arr2[i]) <= DEFAULT_DELTA) {
        throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
      }
    }
  }

  public static void checkArrayNotEqual(final float[] arr1, final float[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length == arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    for (int i = 0; i < arr1.length; i++) {
      if (Math.abs(arr1[i] - arr2[i]) <= DEFAULT_DELTA) {
        throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
      }
    }
  }

  public static void checkArrayNotEqual(final Object[] arr1, final Object[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new FailedCheckEqualException("array is null", "array is null");
    }

    if (arr1.length == arr2.length) {
      throw new FailedCheckEqualException(arr1.length, arr2.length);
    }

    if (Arrays.equals(arr1, arr2)) {
      throw new FailedCheckEqualException(Arrays.toString(arr1), Arrays.toString(arr2));
    }
  }

  // ------------------------------------------------------------------------------------------------------------------

  public static <T> void checkCollectionEqual(
      final Collection<T> collection1, final Collection<T> collection2) {
    if (!collection1.equals(collection2)) {
      throw new FailedCheckEqualException(collection1, collection2);
    }
  }

  public static <T> void checkCollectionNotEqual(
      final Collection<T> collection1, final Collection<T> collection2) {

    if (collection1.equals(collection2)) {
      throw new FailedCheckEqualException(collection1, "different elements");
    }
  }
}
