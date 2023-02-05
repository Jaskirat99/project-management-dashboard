package model;

import java.util.ArrayList;
import java.util.List;
import static model.Utilities.*;

// Represents an individual project that has a collection of transaction entries and the relevant financial statistics
// for the project.
public class Project {

    private String address;
    private double targetSale;
    private double totalCost;
    private int transactionID = 0;
    List<Entry> transactions = new ArrayList<>();

    // REQUIRES: arg not null
    // EFFECTS: Constructs a project and initializes address with given text
    public Project(String address) {

        this.address = address;
    }

    // EFFECTS: Adds a new entry to transactions list with given args, sets that entry's ID to next available ID,
    // increments ID by one for next one. Updates total cost.
    public void createEntry(String payee, String date, String paymentType, Boolean taxPaid, String taxType,
                            double amount) {

        transactions.add(new Entry(payee, date, paymentType, taxPaid, taxType, amount, this.address));
        transactions.get(transactionID).setID(transactionID);
        transactionID++;
        calculateTotalCost();
    }

    // MODIFIES: this
    // EFFECTS: computes sum of all transaction entries
    public void calculateTotalCost() {
        double sum = 0;
        for (int i = 0; i < transactionID; i++) {
            sum += transactions.get(i).getAmount();
        }
        this.totalCost = sum;

    }

    // EFFECTS: Returns transactions array in easy to see format
    public List<String> formatTransactions() {
        List<String> formattedTransactions = new ArrayList<>();

        for (int i = 0; i < transactions.size(); i++) {
            String formattedEntry = "Payee: " + transactions.get(i).getPayee()
                    + " Date: " + transactions.get(i).getDate()
                    + " Amount: " + formatNumbers(transactions.get(i).getAmount())
                    + " Payment Type: " + transactions.get(i).getPaymentType()
                    + " Tax Included: " + transactions.get(i).getTaxPaid()
                    + " Taxes Applied: " + transactions.get(i).getTaxType()
                    + " Project: " + transactions.get(i).getProject();

            formattedTransactions.add(formattedEntry);

        }
        return formattedTransactions;
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
