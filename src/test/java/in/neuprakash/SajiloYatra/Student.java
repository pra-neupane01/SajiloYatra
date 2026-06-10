package in.neuprakash.SajiloYatra;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Student {
    String name;
    int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();

        studentList.add(new Student("prakash", 100));
        studentList.add(new Student("ram", 20));
        studentList.add(new Student("harry", 19));

        Predicate<Student> studentPredicate = t -> t.marks >= 40;
        System.out.println(studentPredicate.test(new Student("hi", 100)));

        Consumer<Student> studentConsumer = t -> System.out.println(t);
        studentConsumer.accept(new Student("New", 111));

        Supplier<String> supplier = () -> "Result Genereated";
        System.out.println(supplier.get());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
