package in.neuprakash.SajiloYatra;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilterPractice {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 15, 20, 25, 30, 35, 40);
        Predicate<Integer> predicate = t -> t % 2 == 0;
        List<Integer> evenNumbers = numbers.stream().filter(predicate).toList();
        evenNumbers.forEach(t -> System.out.println(t));

    }
}
