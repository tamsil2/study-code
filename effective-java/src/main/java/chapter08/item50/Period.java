package chapter08.item50;

import java.util.Date;

// 코드 50-1 기간을 표현하는 클래스 - 불변식을 지키지 못했다.
public final class Period {
    private final Date start;
    private final Date end;

    /**
     * @param start 시작 시간
     * @param end   end 종료 시간. 시작 시간보다 뒤여야 한다.
     * @throws IllegalArgumentException 시작 시간이 종료 시간보다 늦을 때 발생한다.
     * @throws NullPointerException start나 end가 null이면 발생한다.
     */
    public Period(Date start, Date end) {
        if(start.compareTo(end) > 0)
            throw new IllegalArgumentException(start + "가 " + end + "보다 늦다.");
        this.start = start;
        this.end = end;
    }

    public Date start() {
        return start;
    }

    public Date end() {
        return end;
    }

    public String toString() {
        return start + " - " + end;
    }

    // 코드 50-3 수정한 생성자 - 매개변수의 방어적 복사본을 만든다.
//    public Period(Date start, Date end) {
//        this.start = new Date(start.getTime());
//        this.end = new Date(end.getTime());
//
//        if(this.start.compareTo(this.end) > 0)
//            throw new IllegalArgumentException(this.start + "가 " + this.end + "보다 높다.");
//    }

    // 코드 50-5 수정한 접근자 - 필드의 방어적 복사본을 반환한다.
//    public Date start() {
//        return new Date(start.getTime());
//    }
//
//    public Date end() {
//        return new Date(end.getTime());
//    }
}
