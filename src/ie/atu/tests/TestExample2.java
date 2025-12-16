package ie.atu.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestExample2 {
    // Tests must be inside a @Test method to work
    @Test
    void testStringEquality() {
        String expected = "Hello World";
        String actual = "Hello World";
        assertEquals(expected, actual);
    }


}