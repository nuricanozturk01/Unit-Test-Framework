package org.example;

public final class Armstrong {

    public static boolean isArmstrongNumber(int number) {
        var originalNumber = number;
        var digitCount = (int) (Math.log10(originalNumber) + 1);
        var sum = 0;

        while (number > 0) {
            var digit = number % 10;
            sum += Math.pow(digit, digitCount);
            number /= 10;
        }

        return sum == originalNumber;
    }
}
