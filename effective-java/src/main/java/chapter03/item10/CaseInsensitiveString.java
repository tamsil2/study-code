package chapter03.item10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 코드 10-1 잘못된 코드 - 대칭성 위배
public class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    // 대칭성 위배
//    @Override
//    public boolean equals(Object o) {
//        if(o instanceof CaseInsensitiveString) {
//            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
//        }
//        if(o instanceof String) {
//            return s.equalsIgnoreCase((String) o);
//        }
//        return false;
//    }

    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";

        List<CaseInsensitiveString> list = new ArrayList<>();
        list.add(cis);

        System.out.println(list.contains(s));
    }
}
