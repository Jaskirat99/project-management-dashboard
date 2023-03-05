package model;

import org.json.JSONArray;
import org.json.JSONObject;

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

    // EFFECTS: Computes total tax for all transactions in project
    public double calculateTotalTax() {
        double sum = 0;
        for (int i = 0; i < transactionID; i++) {
            sum += transactions.get(i).calculateTax();
        }
        return sum;
    }


    // REQUIRES: num is 1 or 2
    // EFFECTS: Computes a breakdown of different tax types
    public double calculateCertainTax(int num) {
        double amount = 0;
        for (int i = 0; i < transactionID; i++) {
            if (num == 1) {
                for (i = 0; i < transactions.size(); i++) {
                    if (Objects.equals("gst", transactions.get(i).getTaxType().toLowerCase())) {
                        amount += transactions.get(i).calculateTax();
                    } else if (Objects.equals("both", transactions.get(i).getTaxType().toLowerCase())) {
                        amount += (transactions.get(i).calculateTax()) * 5 / 12;
                    }
                }
            } else {
                for (i = 0; i < transactions.size(); i++) {
                    if (Objects.equals("pst", transactions.get(i).getTaxType().toLowerCase())) {
                        amount += transactions.get(i).calculateTax();
                    } else if (Objects.equals("both", transactions.get(i).getTaxType().toLowerCase())) {
                        amount += (transactions.get(i).calculateTax()) * 7 / 12;
                    }
                }
            }
        }
        return amount;
    }

    // REQUIRES: num is 1 or 2 or 3
    // EFFECTS: Computes total cost for specific types of purchases
    public double calculateCostBreakdown(int num) {
        double amount = 0;
        for (int i = 0; i < transactionID; i++) {
            if (num == 1) {
                for (i = 0; i < transactions.size(); i++) {
                    if (Objects.equals("cash", transactions.get(i).getPaymentType().toLowerCase())) {
                        amount += transactions.get(i).getAmount();
                    }
                }
            } else if (num == 2) {
                for (i = 0; i < transactions.size(); i++) {
                    if (Objects.equals("cheque", transactions.get(i).getPaymentType().toLowerCase())) {
                        amount += transactions.get(i).getAmount();
                    }
                }
            } else if (num == 3) {
                for (i = 0; i < transactions.size(); i++) {
                    if (Objects.equals("visa", transactions.get(i).getPaymentType().toLowerCase())) {
                        amount += transactions.get(i).getAmount();
                    }
                }
            }
        }
        return amount;
    }

    // EFFECTS: Returns transactions array in easy to see format
    public List<String> formatTransactions() {
        List<String> formattedTransactions = new ArrayList<>();

        for (Entry transaction : transactions) {
            String formattedEntry = "Payee: " + transaction.getPayee()
                    + " Date: " + transaction.getDate()
                    + " Amount: " + formatNumbers(transaction.getAmount())
                    + " Payment Type: " + transaction.getPaymentType().toUpperCase()
                    + " Tax Included: " + transaction.getTaxPaid()
                    + " Taxes Applied: " + transaction.getTaxType().toUpperCase()
                    + " Project: " + transaction.getProject();

            formattedTransactions.add(formattedEntry);

        }
        return formattedTransactions;
    }

    // EFFECTS: converts project information to JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("address", address);
        json.put("target", targetSale);
        json.put("entries", entriesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray entriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Entry e : transactions) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }


    public String getAddress() {
        return address;
    }


    public double getTargetSale() {
        return targetSale;
    }

    public void setTargetSale(double targetSale) {
        this.targetSale = targetSale;
    }

    public double getTotalCost() {
        calculateTotalCost();
        return totalCost;
    }

    public List<Entry> getAllTransactions() {
        return transactions;
    }

    public Entry getTransaction(int number) {
        return transactions.get(number);
    }

    public int getNumberOfTransactions() {
        return transactions.size(); }
}
