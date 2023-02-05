package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {
    Project p1;
    Project p2;

    @BeforeEach
    public void setup() {
        p1 = new Project("123 Street");
        p2 = new Project("456 Street");

   }

   @Test
    public void projectTest() {
        assertEquals("123 Street", p1.getAddress());
        assertEquals("456 Street", p2.getAddress());
   }

   @Test
    public void createEntryTest() {
        p1.createEntry("payee1", "date1", "cash", true, "GST", 100);
        p1.createEntry("payee2", "date2", "visa", true, "PST", 200);
        p2.createEntry("payee3", "date3", "cheque", true, "BOTH", 300);
        p2.createEntry("payee4", "date4", "cash", false, "GST", 400);

        assertEquals(0,p1.transactions.get(0).getId());
        assertEquals(1,p1.transactions.get(1).getId());
        assertEquals(0, p2.transactions.get(0).getId());
        assertEquals(1, p2.transactions.get(1).getId());

   }

   @Test
    public void calculateTotalCost() {
       p1.createEntry("payee1", "date1", "cash", true, "GST", 100);
       p1.createEntry("payee2", "date2", "visa", true, "PST", 200);
       p2.createEntry("payee3", "date3", "cheque", true, "BOTH", 300);
       p2.createEntry("payee4", "date4", "cash", false, "GST", 400);

       assertEquals(300, p1.getTotalCost());
       assertEquals(700, p2.getTotalCost());


   }

   @Test
    public void formatTransactionsTest(){
       p1.createEntry("payee1", "date1", "cash", true, "GST", 100);
       p2.createEntry("payee4", "date4", "cash", false, "GST", 400);
       p2.createEntry("payee3", "date3", "cheque", true, "BOTH", 300);

       assertEquals("Payee: payee1 Date: date1 Amount: $100.00 Payment Type: cash Tax Included: true " +
               "Taxes Applied: GST Project: 123 Street", p1.formatTransactions().get(0));
       assertEquals("Payee: payee3 Date: date3 Amount: $300.00 Payment Type: cheque Tax Included: true " +
               "Taxes Applied: BOTH Project: 456 Street", p2.formatTransactions().get(1));
   }
}
