package com.example.tbspring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component(value = "message")
public class MessageFactoryBean implements FactoryBean<Message> {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(this.text);
    }

    @Override
    public Class<?> getObjectType() {
        return Message.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
