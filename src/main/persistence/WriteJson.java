package persistence;

import model.Entry;
import model.Project;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.List;

// INSPIRED FROM UBC CPSC 210 JSON SERIALIZATION DEMO
// Represents a writer that writes JSON representation of project to file
public class WriteJson {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file. Sets destination to given destination
    public WriteJson(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of all projects to file
    public void write(List<Project> projects) {
        JSONArray jsonArray = new JSONArray();


        for (int i = 0; i < projects.size(); i++) {
            jsonArray.put(projects.get(i).toJson());
        }
        saveToFile(jsonArray.toString(TAB));


    }


    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}


