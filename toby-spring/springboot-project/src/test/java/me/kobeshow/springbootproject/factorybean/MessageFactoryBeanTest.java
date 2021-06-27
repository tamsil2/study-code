package me.kobeshow.springbootproject.factorybean;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageFactoryBeanTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void getMessageFromFactoryBean() {
        Object message = context.getBean("message");
        assertThat(message.getClass()).isEqualTo(Message.class);
        assertThat(((Message)message).getText()).isEqualTo("Factory Bean");
    }

    @DisplayName("팩토리 빈 자체 테스트")
    @Test
    public void getFactoryBeanTest() {
        Object factory = context.getBean("&message");
        assertThat(factory).isEqualTo(MessageFactoryBean.class);
    }
}
