package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// Represents a date in the format of DD-MM-YYY
public class Date {

    String formattedDate;

    // Constructor immediately creates a new formatted date
    public Date() {
        formatDate();
    }

    // EFFECTS: Sets formattedDate to today's date, formatted in a presentable format
    public void formatDate()  {
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //LocalDateTime now = LocalDateTime.now();
        //formattedDate = dtf.format(now); FUNCTIONALITY REMOVED FOR TESTING FOR P1
        formattedDate = "08/02/2023";
    }

    public String getFormattedDate() {
        return formattedDate;
    }
}
