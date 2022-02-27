package com.example.tbspring.jdk;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.assertThat;


public class ReflectionTest {

    @Test
    public void invokeMethod() throws Exception {
        String name = "Spring";

        Method length = name.getClass().getMethod("length");
        assertThat((Integer) length.invoke(name)).isEqualTo(6);
    }

    @Test
    void simpleProxy() {
        //given
        HelloTarget helloTarget = new HelloTarget();
        HelloUppercase helloUppercase = new HelloUppercase(helloTarget);

        //when
        assertThat(helloTarget.sayHello("toby")).isEqualTo("Hellotoby");
        assertThat(helloUppercase.sayHello("toby")).isEqualTo("HELLOTOBY");
    }

    @Test
    void dynamicProxy() {
        Hello hello = (Hello) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Hello.class}, new UppercaseHandler(new HelloTarget()));
        assertThat(hello.sayHello("name")).isEqualTo("HELLONAME");

    }
}
