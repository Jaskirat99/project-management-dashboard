package model;

import java.text.*;

public final class Utilities {

    // Miscellaneous utility functions
    public Utilities(){}

    public static String formatNumbers(double raw) {
        String formattedStr = "$" + String.format("%.2f",raw);

        return formattedStr;
    }
}
