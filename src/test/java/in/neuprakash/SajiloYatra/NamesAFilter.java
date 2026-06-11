package in.neuprakash.SajiloYatra;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class NamesAFilter {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "Alex",
                "John",
                "Adam",
                "Mike",
                "Andrew"
        );
        Predicate<String> predicate = name -> name.startsWith("A");
        List<String> namesA = names.stream().filter(predicate).toList();
        namesA.forEach(name -> System.out.println(name));
    }
}
