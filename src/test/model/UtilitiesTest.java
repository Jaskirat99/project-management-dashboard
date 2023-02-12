package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import  model.Utilities.*;

import static model.Utilities.findCloseEnoughCorrectInput;
import static org.junit.jupiter.api.Assertions.*;

// Filler Test for utilities constructor for code coverage
public class UtilitiesTest {
    Utilities u1 = new Utilities();

    @Test
    public void utilitiesTest() {
        assertEquals(0, u1.getTestFillerValue());
    }

    @Test
    public void findCloseEnoughCorrectInputTest() throws FileNotFoundException {
        File cash = new File("data/CashSpellings");
        File visa = new File("data/VisaSpellings");
        File cheque = new File("data/ChequeSpellings");

        assertTrue(findCloseEnoughCorrectInput(cash, "cashh"));
        assertTrue(findCloseEnoughCorrectInput(visa, "visaa"));
        assertFalse(findCloseEnoughCorrectInput(cheque, "visa"));
        assertFalse(findCloseEnoughCorrectInput(cash, "fjdkfj"));
    }
}
