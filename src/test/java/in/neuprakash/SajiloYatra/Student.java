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
        studentList.add(new Student("Ram", 45));
        studentList.add(new Student("Sita", 82));
        studentList.add(new Student("Hari", 38));
        studentList.add(new Student("Gita", 22));
        studentList.add(new Student("Nabin", 55));

        Supplier<String> supplier = () -> "Result Generated";
        System.out.println(supplier.get());

        Predicate<Student> studentPredicate = t -> t.marks >= 40;
        Consumer<Student> studentConsumer = t -> System.out.println(t.name + " : Pass");

        studentList.stream()
                .filter(studentPredicate)
                .forEach(studentConsumer);

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
