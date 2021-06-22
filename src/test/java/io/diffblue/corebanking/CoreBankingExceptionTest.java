package io.diffblue.corebanking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;


public class CoreBankingExceptionTest {
    @Test
    public void testConstructor() {
        CoreBankingException actualCoreBankingException = new CoreBankingException("An error occurred");
        assertNull(actualCoreBankingException.getCause());
        assertEquals("io.diffblue.corebanking.CoreBankingException: An error occurred",
                actualCoreBankingException.toString());
        assertEquals(0, actualCoreBankingException.getSuppressed().length);
        assertEquals("An error occurred", actualCoreBankingException.getMessage());
        assertEquals("An error occurred", actualCoreBankingException.getLocalizedMessage());
    }
}

