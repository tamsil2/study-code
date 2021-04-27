package com.kobeshow.extconfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations = "classpath:/test.properties")
@SpringBootTest(properties = "kobeshow.name=Tamsil2")
class SpringbootExtconfigApplicationTests {

    @Autowired
    Environment environment;

    @Test
    void contextLoads() {
        assertThat(environment.getProperty("kobeshow.name"))
                .isEqualTo("Tamsil2");
    }

}
