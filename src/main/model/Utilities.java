package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Scanner;

// Class to hold methods that are useful everywhere
public class Utilities {
    private int testFillerValue;

    // Miscellaneous utility functions
    public Utilities() {}

    // EFFECTS: Takes a double and formats that into a presentable string
    public static String formatNumbers(double raw) {
        String formattedStr = "$" + String.format("%,.2f",raw);
        EventLog.getInstance().logEvent(new Event("Number Formatted For Display!."));

        return formattedStr;
    }

    public int getTestFillerValue() {
        return testFillerValue;
    }

    // EFFECTS: Searches given file of typos to check if user misspelled intended word
    public static boolean findCloseEnoughCorrectInput(File file, String searchString) throws FileNotFoundException {
        boolean result = false;
        Scanner input = new Scanner(new FileReader(file));
        while (input.hasNextLine() && !result) {
            result = Objects.equals(input.nextLine(),searchString);
        }

        EventLog.getInstance().logEvent(new Event("Checking For Errors!"));
        return result;
    }
}

