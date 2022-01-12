import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class JUnitTest {
    static JUnitTest jUnitTest;
    @Autowired
    static ApplicationContext applicationContext = null;
    @Test
    public void test1() {
        assertNotEquals(this, is(not(sameInstance(jUnitTest))));
        jUnitTest = this;

        assertEquals(applicationContext == null || applicationContext == this.applicationContext,
                is(true));
    }

    @Test
    public void test2() {
        assertNotEquals(this, is(not(sameInstance(jUnitTest))));
        jUnitTest = this;
        assertEquals(applicationContext == null || applicationContext == this.applicationContext,
                is(true));
    }

    @Test
    public void test3() {
        assertNotEquals(this, is(not(sameInstance(jUnitTest))));
        jUnitTest = this;
        assertEquals(applicationContext == null || applicationContext == this.applicationContext,
                is(true));
    }
}
