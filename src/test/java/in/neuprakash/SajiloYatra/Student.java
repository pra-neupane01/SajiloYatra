package in.neuprakash.SajiloYatra;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
