package persistence;

import model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


import model.Project;

// INSPIRED BY CPSC 210 JSON SERIALIZATION DEMO
public class ReadJsonTest extends JsonTest{
    List<Project> projects;

    @BeforeEach
    void setup() {
        projects = new ArrayList<>();
    }
    @Test
    void testReaderNonExistentFile() {
        ReadJson reader = new ReadJson("./data/noSuchFile.json");
        try {
            projects = reader.read();
            fail("Should have thrown an IO exception");
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderEmptyProject() {
        ReadJson reader = new ReadJson("./data/testReaderEmptyProject.json");
        try {
            projects = reader.read();
            assertEquals("Empty Project Test", projects.get(0).getAddress());
            assertEquals(0, projects.get(0).getNumberOfTransactions());
            assertEquals(0, projects.get(0).getTargetSale());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

        @Test
        void testReaderGeneralWorkRoom() {
            ReadJson reader = new ReadJson("./data/testReaderGeneralProject.json");
            try {
                projects = reader.read();

                assertEquals("Project 1", projects.get(0).getAddress());
                assertEquals("Project 2", projects.get(1).getAddress());

                assertEquals(100, projects.get(0).getTargetSale());
                assertEquals(200, projects.get(1).getTargetSale());

                List<Entry> entries1 = projects.get(0).getAllTransactions();
                assertEquals(2, entries1.size());

                List<Entry> entries2 = projects.get(1).getAllTransactions();
                assertEquals(1, entries2.size());

                checkEntry("Entry1","Cheque","PST",100.0,false,entries1.get(0));
                checkEntry("Entry2","Visa","BOTH",200.0,true,entries1.get(1));

                checkEntry("Entry3","Cash","GST",300.0,true,entries2.get(0));
            } catch (IOException e) {
                fail("Couldn't read from file");
            }
        }
}
