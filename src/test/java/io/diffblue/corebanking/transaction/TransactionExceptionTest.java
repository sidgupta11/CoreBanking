package io.diffblue.corebanking.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TransactionExceptionTest {
    @Test
    public void testConstructor() {
        TransactionException actualTransactionException = new TransactionException("An error occurred");
        assertNull(actualTransactionException.getCause());
        assertEquals("io.diffblue.corebanking.transaction.TransactionException: An error occurred",
                actualTransactionException.toString());
        assertEquals(0, actualTransactionException.getSuppressed().length);
        assertEquals("An error occurred", actualTransactionException.getMessage());
        assertEquals("An error occurred", actualTransactionException.getLocalizedMessage());
    }
}

