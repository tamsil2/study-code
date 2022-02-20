package me.tamsil.designpatterns._03_behavioral_patterns._20_state._02_after;

public class Client {
    public static void main(String[] args) {
        OnlineCourse onlineCourse = new OnlineCourse();
        Student student = new Student("tamsil");
        Student kobeshow = new Student("kobeshow");
        kobeshow.addPrivate(onlineCourse);

        onlineCourse.addStudent(student);
        onlineCourse.changeState(new Private(onlineCourse));
        onlineCourse.addReview("hello", student);
        onlineCourse.addStudent(kobeshow);

        System.out.println(onlineCourse.getState());
        System.out.println(onlineCourse.getReviews());
        System.out.println(onlineCourse.getStudents());
    }
}
