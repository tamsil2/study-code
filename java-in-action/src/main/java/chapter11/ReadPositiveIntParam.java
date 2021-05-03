package chapter11;

import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class ReadPositiveIntParam {

    public static int readDurationImperative(Properties props, String name){
        String value = props.getProperty(name);
        if(value != null){
            try {
                int i = Integer.parseInt(value);
                if(i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe){}
        }
        return 0;
    }

    public static int readDurationWithOptional(Properties props, String name){
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(ReadPositiveIntParam::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

    public static Optional<Integer> stringToInt(String s){
        try {
            return of(Integer.parseInt(s));
        }catch (NumberFormatException e){
            return empty();
        }
    }
}
