package model;

import java.util.ArrayList;
import java.util.List;

// Represents an individual project that has a collection of transaction entries and the relevant financial statistics
// for the project.
public class Project {

    private String address;
    private double targetSale;
    private double totalCost;
    private static int numOfTransactions = 0;
    List<Entry> transactions = new ArrayList<>();

    // REQUIRES: arg not null
    // EFFECTS: Constructs a project and initializes address with given text
    public Project(String address) {
        this.address = address;
    }

    public void createEntry(String payee, String date, String paymentType, Boolean taxPaid, String taxType,
                            double amount) {
        transactions.add(new Entry(payee, date, paymentType, taxPaid, taxType, amount, this.address));
    }

    // MODIFIES: this
    // EFFECTS: computes sum of all transaction entries
    public void calculateTotalCost() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTargetSale() {
        return targetSale;
    }

    public void setTargetSale(double targetSale) {
        this.targetSale = targetSale;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
