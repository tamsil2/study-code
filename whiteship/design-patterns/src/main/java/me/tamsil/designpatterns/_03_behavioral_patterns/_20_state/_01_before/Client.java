package me.tamsil.designpatterns._03_behavioral_patterns._20_state._01_before;

public class Client {
    public static void main(String[] args) {
        Student student = new Student("tamsil");
        OnlineCourse onlineCourse = new OnlineCourse();

        Student hong = new Student("hong");
        hong.addPrivateCourse(onlineCourse);

        onlineCourse.addStudent(student);
        onlineCourse.changeState(OnlineCourse.State.PRIVATE);

        onlineCourse.addStudent(hong);

        onlineCourse.addReview("hello", student);

        System.out.println(onlineCourse.getState());
        System.out.println(onlineCourse.getStudents());
        System.out.println(onlineCourse.getReviews());
    }
}
