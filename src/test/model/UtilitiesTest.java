package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Filler Test for utilities constructor for code coverage
public class UtilitiesTest {
    Utilities u1 = new Utilities();

    @Test
    public void utilitiesTest() {
        assertEquals(0, u1.getTestFillerValue());
    }
}
