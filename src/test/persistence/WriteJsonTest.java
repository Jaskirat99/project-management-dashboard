package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

// INSPIRED BY CPSC 210 JSON SERIALIZATION DEMO
public class WriteJsonTest extends JsonTest{
    List<Project> projects;

    @BeforeEach
    void setup() {
        projects = new ArrayList<>();
    }
    @Test
    void testWriterInvalidFile() {
        try {
            Project project1 = new Project("Project1");
            projects.add(project1);
            WriteJson writer = new WriteJson("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Project project1 = new Project("Project1");
            projects.add(project1);
            WriteJson writer = new WriteJson("./data/testWriterEmptyProject.json");
            writer.open();
            writer.write(projects);
            writer.close();

            ReadJson reader = new ReadJson("./data/testWriterEmptyProject.json");
            projects = reader.read();
            assertEquals("Project1", projects.get(0).getAddress());
            assertEquals(0, projects.get(0).getNumberOfTransactions());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Project project1 = new Project("Project1");
            projects.add(project1);
            projects.get(0).createEntry("Entry1","Cash",true,"GST",100);
            projects.get(0).createEntry("Entry2","Cheque",false,"PST",200);
            WriteJson writer = new WriteJson("./data/testWriterGeneralProject.json");
            writer.open();
            writer.write(projects);
            writer.close();

            ReadJson reader = new ReadJson("./data/testWriterGeneralProject.json");
            projects = reader.read();
            assertEquals("Project1", projects.get(0).getAddress());
            List<Entry> entries = projects.get(0).getAllTransactions();
            assertEquals(2, entries.size());
            checkEntry("Entry1","Cash","GST",100.0,true,entries.get(0));
            checkEntry("Entry2","Cheque","PST",200.0,false,entries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

