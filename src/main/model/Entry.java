package model;


import org.json.JSONObject;

// Represents an individual transaction storing ID, project, payee, amount, date, payment type, tax paid
public class Entry {

    private String payee;
    private String paymentType;
    private Boolean taxPaid;
    private String taxType;
    private double amount;
    private String project;
    private int id;

    // REQUIRES: All args are not empty, amount > 0
    // EFFECTS: Initializes all fields with given arguments
    public Entry(String payee, String paymentType, Boolean taxPaid, String taxType, double amount,
                 String project) {
        this.payee = payee;
        this.paymentType = paymentType;
        this.taxPaid = taxPaid;
        this.taxType = taxType;
        this.amount = amount;
        this.project = project;

    }

    // REQUIRES: if tax paid == true, tax type can not be none
    // EFFECTS: Returns amount of tax that was paid on amount
    public double calculateTax() {
        EventLog.getInstance().logEvent(new Event("Tax Calculated For Entry."));
        if (taxPaid) {
            if (taxType.equals("GST")) {
                return ((amount - (amount / 1.05)));
            } else if (taxType.equals("PST")) {
                return (amount - (amount / 1.07));
            } else {
                return (amount - (amount / 1.12));
            }
        } else {
            switch (taxType) {
                case "GST":
                    return (amount * 0.05);
                case "PST":
                    return (amount * 0.07);
                case "BOTH":
                    return (amount * 0.12);
                default:
                    return (0);
            }
        }

    }

    // EFFECTS: converts project information to JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("payee", payee);
        json.put("paymentType", paymentType);
        json.put("taxPaid",taxPaid);
        json.put("amount", amount);
        json.put("project", project);
        json.put("taxType",taxType);
        json.put("ID",id);
        return json;
    }

    public void setPayee(String newPayee) {
        this.payee = newPayee;
    }

    public void setPaymentType(String newPaymentType) {
        this.paymentType = newPaymentType;
    }

    public void setTaxPaid(Boolean newTaxPaid) {
        this.taxPaid = newTaxPaid;
    }

    public void setAmount(double newAmount) {
        this.amount = newAmount;
    }

    public void setProject(String newProject) {
        this.project = newProject;
    }

    public void setTaxType(String newTaxType) {
        this.taxType = newTaxType;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getPayee() {
        return payee;
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
