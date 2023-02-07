package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public void createEntry(String payee, String paymentType, Boolean taxPaid, String taxType,
                            double amount) {

        transactions.add(new Entry(payee, paymentType, taxPaid, taxType, amount, this.address));
        transactions.get(transactionID).setID(transactionID);
        transactionID++;
        calculateTotalCost();
    }

    // MODIFIES: this
    // EFFECTS: computes sum of all transaction entries
    public void calculateTotalCost() {
        double sum = 0;
        for (Entry transaction : transactions) {
            sum += transaction.getAmount();
        }
        this.totalCost = sum;

    }

    public double calculateTotalTax() {
        double sum = 0;
        for (int i = 0; i < transactionID; i++) {
            sum += transactions.get(i).calculateTax();
        }
        return sum;
    }

    //TODO fix this!!
    public double calculateCertainTax(int num) {

        for (int i = 0; i < transactionID; i++) {
            if (num == 1) {
                if (Objects.equals("GST", transactions.get(i).getTaxType())) {
                    num += transactions.get(i).calculateTax();
                }
            } else {
                if (Objects.equals("PST", transactions.get(i).getTaxType())) {
                    num += transactions.get(i).calculateTax();
                }
            }
        }
        return num;
    }

    // EFFECTS: Returns transactions array in easy to see format
    public List<String> formatTransactions() {
        List<String> formattedTransactions = new ArrayList<>();

        for (Entry transaction : transactions) {
            String formattedEntry = "Payee: " + transaction.getPayee()
                    + " Date: " + transaction.getDate()
                    + " Amount: " + formatNumbers(transaction.getAmount())
                    + " Payment Type: " + transaction.getPaymentType()
                    + " Tax Included: " + transaction.getTaxPaid()
                    + " Taxes Applied: " + transaction.getTaxType()
                    + " Project: " + transaction.getProject();

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

    public List<Entry> getAllTransactions() {
        return transactions;
    }

    public Entry getTransaction(int number) {
        return transactions.get(number);
    }
}
