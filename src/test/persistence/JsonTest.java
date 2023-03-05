package persistence;

import model.Entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEntry(String payee, String paymentType, String taxType, Double amount, Boolean taxPaid,
                              Entry entry) {
        assertEquals(payee, entry.getPayee());
        assertEquals(paymentType, entry.getPaymentType());
        assertEquals(taxType, entry.getTaxType());
        assertEquals(amount, entry.getAmount());
        assertEquals(taxPaid, entry.getTaxPaid());

    }
}
