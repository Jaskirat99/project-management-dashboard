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
        p1.createEntry("payee1",  "cash", true, "GST", 100);
        p1.createEntry("payee2",  "visa", true, "PST", 200);
        p2.createEntry("payee3",  "cheque", true, "BOTH", 300);
        p2.createEntry("payee4",  "cash", false, "GST", 400);
   }

   @Test
    public void projectTest() {
        assertEquals("123 Street", p1.getAddress());
        assertEquals("456 Street", p2.getAddress());
   }

   @Test
    public void createEntryTest() {


        assertEquals(0,p1.transactions.get(0).getId());
        assertEquals(1,p1.transactions.get(1).getId());
        assertEquals(0, p2.transactions.get(0).getId());
        assertEquals(1, p2.transactions.get(1).getId());

   }

   @Test
    public void calculateTotalCostTest() {


       assertEquals(300, p1.getTotalCost());
       assertEquals(700, p2.getTotalCost());


   }

    @Test
    public void calculateTotalTaxTest() {


        assertEquals(17.846, p1.calculateTotalTax(), 1);
        assertEquals(52.142, p2.calculateTotalTax(), 1);


    }

    @Test
    public void calculateCertainTaxTest() {


        assertEquals(13.084, p1.calculateCertainTax(2), 1);
        assertEquals(33.39, p2.calculateCertainTax(1), 1);


    }

   @Test
    public void formatTransactionsTest(){


       assertEquals("Payee: payee1 Date: 08/02/2023 Amount: $100.00 Payment Type: CASH Tax Included: true " +
               "Taxes Applied: GST Project: 123 Street", p1.formatTransactions().get(0));
       assertEquals("Payee: payee4 Date: 08/02/2023 Amount: $400.00 Payment Type: CASH Tax Included: false " +
               "Taxes Applied: GST Project: 456 Street", p2.formatTransactions().get(1));
   }

   @Test
    public void calculateCostBreakdownTest(){
        assertEquals(100,p1.calculateCostBreakdown(1));
        assertEquals(300,p2.calculateCostBreakdown(2));
        assertEquals(200,p1.calculateCostBreakdown(3));
   }
}
