package chapter03.item11;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PhoneNumber {
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 9999, "line num");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if(val < 0 || val > max) {
            throw new IllegalArgumentException(arg + ": " + val);
        }
        return (short) val;
    }

    public static void main(String[] args) {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber(707, 867, 5309), "제니");
        System.out.println(m.get(new PhoneNumber(707, 867, 5309)));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return areaCode == that.areaCode && prefix == that.prefix && lineNum == that.lineNum;
    }

    // 코드 11-2 전형적인 hashCode 메서드
/*    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }*/

    // 코드 11-3 한 줄짜리 hashCode 메서드 - 성능이 살짝 아쉽다.
    /*@Override
    public int hashCode() {
        return Objects.hash(lineNum, prefix, areaCode);
    }*/

    // 코드 11-4 해시코드를 지연 쵝화하는 hashCode 메서드 - 스레드 안전성까지 고려해야 한다.
    private int hashCode;

    @Override
    public int hashCode() {
        int result = hashCode;
        if(result == 0) {
            result = Short.hashCode(areaCode);
            result = 31 * result + Short.hashCode(prefix);
            result = 31 * result + Short.hashCode(lineNum);
            hashCode = result;
        }
        return result;
    }
}
