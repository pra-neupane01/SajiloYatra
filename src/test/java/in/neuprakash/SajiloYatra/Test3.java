package in.neuprakash.SajiloYatra;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Create a program using Supplier<String> that:
 * Generates a random OTP of length 6.
 * Print the OTP.
 * Example:
 * 834921
 */

public class Test3 {
    public static void main(String[] args) {
        Random rand = new Random();
        Supplier<String> supplier = () -> rand.nextInt(1000000) + "BNK";
        System.out.println(supplier.get());

    }
}
