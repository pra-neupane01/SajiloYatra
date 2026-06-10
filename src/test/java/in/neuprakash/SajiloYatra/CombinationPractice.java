package in.neuprakash.SajiloYatra;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * List<Integer> prices = Arrays.asList(100, 250, 500, 50, 900);
 * Create a Predicate<Integer> to check price above 200.
 * Create a Consumer<Integer> to print:
 * Expensive item: 250
 * Expensive item: 500
 * ...
 * Use a Supplier<String> to print:
 * Shopping Started
 */
public class CombinationPractice {

    public static void main(String[] args) {

        Predicate<Integer> predicate = t -> t > 200;
        List<Integer> prices = Arrays.asList(100, 250, 500, 50, 900);

        for (Integer price : prices) {
            if (predicate.test(price)) {
                System.out.println(price);
            }
        }
        Consumer<Integer> consumer = t -> System.out.println("Expensive Item: " + t);
        prices.forEach(consumer);

        Supplier<String> supplier = () -> "Shopping Started";
        System.out.println(supplier.get());


    }
}
