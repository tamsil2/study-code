package me.kobeshow.beanoverriding;

import me.kobeshow.demofamilymanprep.FamilyMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootBeanoverridingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBeanoverridingApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            FamilyMan familyMan;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                System.out.println("==================");
                System.out.println(familyMan.getName());
                System.out.println("==================");
            }
        };
    }

    @Bean
    public FamilyMan familyMan() {
        FamilyMan familyMan = new FamilyMan();
        familyMan.setName("tamsil");
        return familyMan;
    }
}
