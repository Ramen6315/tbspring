package com.example.tbspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FactoryBeanTest {
    @Autowired
    ApplicationContext context;

    @Autowired
    MessageFactoryBean messageFactoryBean;

    @Test
    public void getMessageFromFactoryBean() {
        //given
        messageFactoryBean.setText("Factory Bean");
        Object message = context.getBean("message");
        Message message1 = (Message) message;

        //then
        assertThat(message).isInstanceOf(Message.class);
        assertThat(message1.getText()).isEqualTo("Factory Bean");
    }
}
