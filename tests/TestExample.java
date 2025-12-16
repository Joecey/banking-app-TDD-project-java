import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestExample {
    // Tests must be inside a @Test method to work
    @Test
    void testStringEquality() {
        String expected = "Hello World";
        String actual = "Hello World";
        assertEquals(expected, actual);
    }

    // Tests must be inside a @Test method to work
    @Test
    void testAnotherTest() {
        String expected = "Hello World2";
        String actual = "Hello World2";
        assertEquals(expected, actual);
    }
}