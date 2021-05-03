package chapter12;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Calendar;
import java.util.Date;

public class DateTimeExamples {
    private static final ThreadLocal<DateFormat> formatters = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("dd-MMM-yyyy");
        }
    };

    public static void main(String[] args){
        useOldDate();
        useLocalDate();
    }

    private static void useOldDate(){
        Date date = new Date(114, 2, 18);
        System.out.println(date);

        System.out.println(formatters.get().format(date));
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.FEBRUARY, 18);
        System.out.println(calendar);
    }

    private static void useLocalDate(){
        LocalDate date = LocalDate.of(2021, 3, 3);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        System.out.println(date);

        int y = date.get(ChronoField.YEAR);
        int m = date.get(ChronoField.MONTH_OF_YEAR);
        int d = date.get(ChronoField.DAY_OF_MONTH);

        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println(time);

        // 예제 12-4 LocalDateTime을 직접 만드는 방법과 날짜와 시간을 조합하는 방법
        LocalDateTime dt1 = LocalDateTime.of(2017, Month.SEPTEMBER, 21, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt1);

        LocalDate date1 = dt1.toLocalDate();
        System.out.println(date1);
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(time1);

        Instant instant = Instant.ofEpochSecond(44 * 365 * 86400);
        Instant now = Instant.now();

        Duration d1 = Duration.between(LocalTime.of(13, 45, 10), time);
        Duration d2 = Duration.between(instant, now);
        System.out.println(d1.getSeconds());
        System.out.println(d2.getSeconds());
    }

    private static class NextWorkingDay implements TemporalAdjuster {

        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if(dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }
}
