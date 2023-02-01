package model;

// Represents an individual transaction storing ID, project, payee, amount, date, payment type, tax paid
public class Entry {

    private String payee;
    private String date;
    private String paymentType;
    private Boolean taxPaid;
    private String taxType;
    private double amount;
    private String project;
    private int id;
    private int nextID = 1;

    // REQUIRES: All args are not empty, amount > 0
    // EFFECTS: Initializes all fields with given arguments
    public Entry(String payee, String date, String paymentType, Boolean taxPaid, String taxType, double amount,
                 String project) {
        this.payee = payee;
        this.date = date;
        this.paymentType = paymentType;
        this.taxPaid = taxPaid;
        this.taxType = taxType;
        this.amount = amount;
        this.project = project;
        this.id = nextID;
        nextID++;
    }

    // EFFECTS: Returns amount of tax that was paid on amount
    public double calculateTaxPaid() {
        if (taxType == "GST") {
            return (amount / 1.05);
        } else if (taxType == "PST") {
            return (amount / 1.07);
        } else if (taxType == "BOTH") {
            return (amount / 1.12);
        } else {
            return 0;
        }
    }

    public void setPayee(String newPayee) {
        this.payee = newPayee;
    }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    public void setPaymentType(String newPaymentType) {
        this.paymentType = newPaymentType;
    }

    public void setTaxPaid(Boolean newTaxPaid) {
        this.taxPaid = newTaxPaid;
    }

    public void setAmount(int newAmount) {
        this.amount = newAmount;
    }

    public void setProject(String newProject) {
        this.project = newProject;
    }

    public void setTaxType(String newTaxType) {

        this.taxType = newTaxType;
    }

    public String getPayee() {
        return payee;
    }

    public String getDate() {
        return date;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Boolean getTaxPaid() {
        return taxPaid;
    }

    public double getAmount() {
        return amount;
    }

    public String getProject() {
        return project;
    }

    public int getId() {
        return id;
    }

    public String getTaxType() {

        return taxType;
    }
}
