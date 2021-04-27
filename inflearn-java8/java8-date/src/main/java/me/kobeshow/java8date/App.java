package me.kobeshow.java8date;

import org.springframework.cglib.core.Local;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) throws InterruptedException{
        Instant instant = Instant.now();
        System.out.println(instant); // 기준시 UTC, GMT

        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime birthDay = LocalDateTime.of(1983, Month.AUGUST, 7, 0, 0, 0);

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);

        Instant nowInstant = Instant.now();
        ZonedDateTime zondedDateTime = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zondedDateTime);

        // 시간 비교1
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthDAy = LocalDate.of(2021, Month.AUGUST, 15);

        Period period = Period.between(today, thisYearBirthDAy);
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthDAy);
        System.out.println(until.get(ChronoUnit.DAYS));

        // 시간 비교2
        Instant instantNow = Instant.now();
        Instant plus = instantNow.plus(10, ChronoUnit.SECONDS);
        Duration instantBetween = Duration.between(instantNow, plus);
        System.out.println(instantBetween.getSeconds());

        // Formatting
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(localDateTime.format(MMddyyyy));

        // Parsing
        LocalDate parse = LocalDate.parse("08/07/1983", MMddyyyy);
        System.out.println(parse);

        // 이전 버전과의 호환
        Date date = new Date();
        Instant convertDate = date.toInstant();
        Date newDate = Date.from(convertDate);
    }
}
