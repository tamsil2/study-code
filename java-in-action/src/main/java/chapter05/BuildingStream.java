package chapter05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class BuildingStream {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Medern", "Java", "In", "Action");
        // 값으로 스트림 만들기
        stream.map(String::toUpperCase).forEach(System.out::println);

        // null이 될 수 있는 객체로 스트림 만들기
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        Stream<String> homeValueStream2 = Stream.ofNullable(System.getProperty("home"));
        Stream<String> values = Stream.of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));

        // 배열로 스트림 만들기
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();

        // 파일로 스트림 만들기
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 함수로 무한 스트림 만들기 - iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // 함수로 무한 스트림 만들기 - generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
