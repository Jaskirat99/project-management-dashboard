package model;

import exceptions.InvalidInputException;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// Represents a date in the format of DD-MM-YYY
public class Date {

    String formattedDate;

    public Date() {
        formatDate();
    }

    public void formatDate()  {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        formattedDate = dtf.format(now);
    }

    public String getFormattedDate() {
        return formattedDate;
    }
}
