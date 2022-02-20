package me.tamsil.designpatterns._01_creational_patterns._05_prototype._03_java;

import java.util.ArrayList;
import java.util.List;

public class JavaCollectionExample {
    public static void main(String[] args) {
        Student tamsil = new Student("tamsil");
        Student kobeshow = new Student("kobeshow");
        List<Student> students = new ArrayList<>();
        students.add(tamsil);
        students.add(kobeshow);

        List<Student> clone = new ArrayList<>(students);
        System.out.println(clone);
    }
}
