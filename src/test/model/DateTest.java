package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {
    Date date1;
    Date date2;


    @BeforeEach
    public void setup(){
        date1 = new Date();
        date2 = new Date();

    }

    @Test
    public void dateTest(){

        assertEquals("08/02/2023",date1.getFormattedDate());
        assertEquals("08/02/2023",date2.getFormattedDate());
    }


}
