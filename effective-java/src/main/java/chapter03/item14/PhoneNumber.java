package chapter03.item14;

import java.util.Comparator;
import java.util.Objects;

public final class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "지역코드");
        this.prefix = rangeCheck(prefix, 999, "프리픽스");
        this.lineNum = rangeCheck(lineNum, 9999, "가입자 번호");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max)
            throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof chapter03.item11.PhoneNumber))
            return false;
        PhoneNumber that = (PhoneNumber) o;
        return areaCode == that.areaCode && prefix == that.prefix && lineNum == that.lineNum;
    }

    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }

    /**
     * 이 전화번호의 문자열 표현을 반환한다.
     * 이 문자열은 "XXX-YYY-ZZZZ" 형태의 12글자로 구성된다.
     * XXX는 지역 코드, YYY는 프리픽스, ZZZZ는 가입자 번호다.
     * 각각의 대문자는 10진수 숫자 하나를 나타낸다.
     *
     * 전화번호의 각 부분의 값이 너무 작아서 자릿수를 채울 수 없다면,
     * 앞에서부터 0으로 채워나간다. 예컨대 가입자 번호가 123이라면
     * 전화번호의 마지막 네 문자는 "0123"이 된다.
     */

    @Override
    public String toString() {
        return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
    }

    // 코드 14-2 기본 타입 필드가 여럿일 때의 비교자
//    @Override
//    public int compareTo(PhoneNumber pn) {
//        int result = Short.compare(areaCode, pn.areaCode);   // 가장 중요한 필드
//        if (result == 0) {
//            result = Short.compare(prefix, pn.prefix);       // 두 번째로 중요한 필드
//            if(result == 0) {
//                result = Short.compare(lineNum, pn.lineNum); // 세 번째로 중요한 필드
//            }
//        }
//        return result;
//    }

    // 코드 14-3 비교자생성 메서드를 활용한 비교자
    private static final Comparator<PhoneNumber> COMPARATOR =
            Comparator.comparingInt((PhoneNumber pn) -> pn.areaCode)
            .thenComparing(pn -> pn.prefix)
            .thenComparing(pn -> pn.lineNum);

    public int compareTo(PhoneNumber pn) {
        return COMPARATOR.compare(this, pn);
    }
}
