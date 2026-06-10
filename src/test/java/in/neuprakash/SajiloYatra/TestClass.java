package in.neuprakash.SajiloYatra;

/*
List<String> names = Arrays.asList("Ram", "Sita", "Hari");
Create a Consumer<String> to print each name with "Hello ".
**/

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TestClass {

    public static void main(String[] args) {
        Consumer<String> consumer = t -> System.out.println("Hello" + t);
        List<String> names = Arrays.asList("Ram", "Sita", "Hari");
        names.forEach(consumer);
    }
}
