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


        assertEquals(0,p1.getTransaction(0).getId());
        assertEquals(1,p1.getTransaction(1).getId());
        assertEquals(0, p2.getTransaction(0).getId());
        assertEquals(1, p2.getTransaction(1).getId());

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
        p1.createEntry("payee5", "cash",false,"BOTH",500);
        p2.createEntry("payee6", "cheque",true,"PST",600);
        p2.createEntry("payee7", "cash",false,"BOTH",700);
        p1.createEntry("payee8", "visa",true,"BOTH",800);

        assertEquals(98.084, p1.calculateCertainTax(2), 1);
        assertEquals(68.392, p2.calculateCertainTax(1), 1);


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
       p1.createEntry("payee8", "visa",true,"BOTH",800);
       p1.createEntry("payee9", "visa",true,"BOTH",900);
       p2.createEntry("payee10", "visa",false,"GST",1000);
       p2.createEntry("payee11", "visa",false,"PST",1000);

       assertEquals(100,p1.calculateCostBreakdown(1));
       assertEquals(400,p2.calculateCostBreakdown(1));
       assertEquals(0,p1.calculateCostBreakdown(2));
       assertEquals(300,p2.calculateCostBreakdown(2));
       assertEquals(1900,p1.calculateCostBreakdown(3));
       assertEquals(2000,p2.calculateCostBreakdown(3));

   }

   @Test
    public void targetSaleTest() {
        p1.setTargetSale(100);
        p2.setTargetSale(1234);

        assertEquals(100,p1.getTargetSale());
        assertEquals(1234, p2.getTargetSale());
   }

   @Test
    public void transactionRetrievalTest() {

        assertEquals(2, p1.getAllTransactions().size());
        assertEquals(2, p2.getAllTransactions().size());
   }
}
