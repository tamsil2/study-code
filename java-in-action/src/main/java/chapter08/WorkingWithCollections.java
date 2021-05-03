package chapter08;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class WorkingWithCollections {
    public static void main(String[] args) {
//        workingWithLists();
//        workingWithMaps();
//        computingOnMaps();
//        removingFromMaps();
//        replacingInMaps();
        mergingMaps();
    }

    private static void workingWithLists() {
        System.out.println("------ workingWithLists Start");

        List<String> referenceCodes = Arrays.asList("a12", "c14", "b13");

        referenceCodes.stream().map(code -> Character.toUpperCase(code.charAt(0)) +
                code.substring(1))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // ListIterator 객체 이용
        for(ListIterator<String> iterator = referenceCodes.listIterator(); iterator.hasNext(); ) {
            String code = iterator.next();
            iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
        }

        // Java8의 기능 이용
        referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));
        System.out.println(referenceCodes);
    }

    private static void workingWithMaps() {
        System.out.println("------ workingWithMaps");

        Map<String, Integer> ageOfFriends = Map.of("Raphael", 30, "Olivia", 25, "Thibaut", 26);

        // Map.Entry<K, V>의 반복자를 이용해 맵의 항목 집합을 반복
        for(Map.Entry<String, Integer> entry: ageOfFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend + " is " + age + " years old");
        }

        // Java 8의 forEach 메서드 이용
        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " + age + " years old"));

        // Entry.comparingByKey 정렬
        Map<String, String> favoriteMovies
                = Map.ofEntries(entry("Raphael", "Star Wars"),
                entry("Cristina", "Matrix"),
                entry("Olivia", "James Bond"));

        favoriteMovies
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);

        // getOrDefault 메서드
        Map<String, String> favoriteMovies2
                = Map.ofEntries(entry("Raphael", "Star Wars"),
                entry("Olivia", "James Bond"));
        System.out.println(favoriteMovies2.getOrDefault("Olivia", "Matrix"));
        System.out.println(favoriteMovies2.getOrDefault("Thibaut", "Matrix"));
    }

    private static void computingOnMaps() {
        System.out.println("------ computingOnMaps Start ------");
        Map<String, List<String>> friendsToMovies = new HashMap<>();

        String friend = "Raphael";
        List<String> movies = friendsToMovies.get(friend);
        if(movies == null) {
            movies = new ArrayList<>();
            friendsToMovies.put(friend, movies);
        }
        movies.add("Star Wars");

        System.out.println(friendsToMovies);

        // computeIfAbsent 사용
        friendsToMovies.computeIfAbsent("Raphael", name -> new ArrayList<>()).add("Star Wars");
    }

    private static void removingFromMaps() {
        Map<String, String> favoriteMovies = new HashMap<>();
        favoriteMovies.put("Raphael", "Jack Reacher 2");
        favoriteMovies.put("Cristina", "Matrix");
        favoriteMovies.put("Olivia", "James Bond");
        String key = "Raphael";
        String value = "Jack Reacher 2";

        // 기존 삭제 패턴
        boolean result = remove(favoriteMovies, key, value);
        System.out.printf("%s [%b]%n", favoriteMovies, result);

        // 삭제 데이터 다시 복원
        favoriteMovies.put("Raphael", "Jack Reacher 2");

        // Java 8의 remove 사용
        favoriteMovies.remove(key, value);
        System.out.printf("%s [%b]%n", favoriteMovies, result);
    }

    private static <K, V> boolean remove(Map<K, V> favoriteMovies, K key, V value) {
        if(favoriteMovies.containsKey(key) && Objects.equals(favoriteMovies.get(key), value)) {
            favoriteMovies.remove(key);
            return true;
        } else {
            return false;
        }
    }

    private static void replacingInMaps() {
        Map<String, String> favoriteMovies = new HashMap<>();
        favoriteMovies.put("Raphael", "Star Wars");
        favoriteMovies.put("Olivia", "james bond");
        favoriteMovies.replaceAll((friend, movie) -> movie.toUpperCase());
        System.out.println(favoriteMovies);
    }

    private static void mergingMaps() {
        Map<String, String> family = Map.ofEntries(
                entry("Teo", "Star Wars"),
                entry("Cristina", "James Bond")
        );
        Map<String, String> friends = Map.ofEntries(
                entry("Raphael", "Star Wars")
        );
        Map<String, String> everyone = new HashMap<>(family);
        everyone.putAll(friends);
        System.out.println(everyone);

        // merge 메서드 사용
        Map<String, String> family2 = Map.ofEntries(
                entry("Teo", "Star Wars"),
                entry("Cristina", "James Bond")
        );
        Map<String, String> friends2 = Map.ofEntries(
                entry("Raphael", "Star Wars"),
                entry("Cristina", "Matrix")
        );
        Map<String, String> everyone2 = new HashMap<>(family);
        friends2.forEach((k, v) ->
                everyone2.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));
        System.out.println(everyone2);
    }
}
