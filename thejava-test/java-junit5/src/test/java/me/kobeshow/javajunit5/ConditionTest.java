package me.kobeshow.javajunit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class ConditionTest {

    @Test
    @DisplayName("assumingThat을 사용한 조건에 따른 테스트")
    void test() {
        String test_env = System.getenv("TEST_ENV");

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("local");
            Study localStudy = new Study(100);
            assertThat(localStudy.getLimit()).isGreaterThan(0);
        });

        assumingThat("tamsil".equalsIgnoreCase(test_env), () -> {
            System.out.println("tamsil");
            Study tamsilStudy = new Study(10);
            assertThat(tamsilStudy.getLimit()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("애노테이션을 이용한 조건 테스트")
    @EnabledOnOs(OS.MAC)
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11})
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "local")
    void test2() {
        String test_env = System.getenv("TEST_ENV");

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("local");
            Study localStudy = new Study(100);
            assertThat(localStudy.getLimit()).isGreaterThan(0);
        });

        assumingThat("tamsil".equalsIgnoreCase(test_env), () -> {
            System.out.println("tamsil");
            Study tamsilStudy = new Study(10);
            assertThat(tamsilStudy.getLimit()).isGreaterThan(0);
        });
    }
}
