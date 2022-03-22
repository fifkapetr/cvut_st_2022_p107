package lab03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class FooTests {

    Foo foo;

    @BeforeEach
    public void setFoo() {
        foo = new Foo();
    }

    @Test
    @Order(0)
    public void returnNumber_returnsFive_passed() {
        Assertions.assertEquals(5, foo.returnNumber());
    }

    @Test
    @Order(1)
    public void returnNumber_returnsFive_failure() {
        Assertions.assertEquals(4, foo.returnNumber());
    }

    @Test
    @Order(2)
    public void getNum_returnsZero_passed() {
        Assertions.assertEquals(0, foo.getNum());
    }

    @Test
    @Order(3)
    public void increment_returnsIncrementedNumber_passed() {
        int number = foo.getNum();
        foo.increment();
        Assertions.assertEquals(++number, foo.getNum());
    }

    @Test
    @Order(4)
    public void throwExceptions_returnsException_throwedException() {
        Assertions.assertThrows(Exception.class, foo::exceptionThrown);
    }

    @Test
    @Order(5)
    public void throwExceptions_returnsException_throwedRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, foo::exceptionThrown);
    }
}
