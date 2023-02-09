package model;

// Class to hold methods that are useful everywhere
public class Utilities {

    // Miscellaneous utility functions
    public Utilities(){}

    // EFFECTS: Takes a double and formats that into a presentable string
    public static String formatNumbers(double raw) {
        String formattedStr = "$" + String.format("%,.2f",raw);

        return formattedStr;
    }


}
