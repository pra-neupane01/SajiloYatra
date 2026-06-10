package in.neuprakash.SajiloYatra;


import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/*
List<Integer> nums = Arrays.asList(10, 15, 20, 25, 30);
Create a Predicate<Integer> to print only even numbers.
 */
public class Test2Class {

    public static void main(String[] args) {

        Predicate<Integer> predicate = t -> t % 2 == 0;
        List<Integer> nums = Arrays.asList(10, 15, 20, 25, 30);

        for (Integer num : nums) {
            if (predicate.test(num)) {
                System.out.println(num);
            }
        }
    }

}
