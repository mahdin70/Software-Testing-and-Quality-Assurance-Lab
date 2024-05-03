package org.example;
import java.lang.Math;


public class Prime {

    public static boolean isPrime(double n) {
        if (n < 2 || n > 1000000000000L) {
            throw new IllegalArgumentException("Input must be in the range 2..1000000000000 inclusive");
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

