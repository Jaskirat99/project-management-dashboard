package ui;

import java.io.FileNotFoundException;

// Main class only to start app class
public class Main {

    // EFFECTS: Creates new instance of App class
    public static void main(String[] args) throws FileNotFoundException {
        try {
            new App();
        } catch (Exception e) {
            System.out.println("Something went wrong! Shutting down...");
        }
    }
}

