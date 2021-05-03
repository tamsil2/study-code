package chapter03.item10.composition;

import chapter03.item10.Color;
import chapter03.item10.Point;

import java.util.Objects;

// 코드 10-5 equals 규약을 지키면서 값 추가하기
public class ColorPoint {
    private final Point point;
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof ColorPoint))
            return false;
        ColorPoint cp = (ColorPoint) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, color);
    }
}
