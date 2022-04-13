package me.tamsil.chapter01.item01;

import java.util.ArrayList;
import java.util.List;

/**
 * 이 클래스의 인스턴스는 #getInstance()를 통해 사용한다.
 * @see #getinstance()
 */
public class Settings {
    private boolean useAutoSteering;

    private boolean useABS;

    private Difficulty difficulty;

    private Settings() {}

    private static final Settings settings = new Settings();

    public static Settings getinstance() {
        return settings;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List.of("tamsil", "kobeshow");
    }
}
