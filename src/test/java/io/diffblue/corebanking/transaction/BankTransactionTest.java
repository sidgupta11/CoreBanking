package io.diffblue.corebanking.transaction;

import static org.junit.Assert.assertEquals;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;

import java.util.Date;

import org.junit.Rule;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BankTransactionTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testConstructor() {
        Date date = new Date(1L);
        Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

        BankTransaction actualBankTransaction = new BankTransaction(10L, date, sourceAcc,
                new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

        assertEquals("1234567890", actualBankTransaction.getSource());
        assertEquals(
                "Transaction: | 70.01.01\t| Source: 1234567890\t| Target: 1234567890\t| Amount: 10\t| Balance: 0\t| Transaction"
                        + " state: NOT_EXECUTED_YET\t|",
                actualBankTransaction.toString());
        assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualBankTransaction.getTransactionState());
        assertEquals(10L, actualBankTransaction.getTransactionAmount());
        assertEquals("1234567890", actualBankTransaction.getTarget());
    }

    @Test
    public void testGetSource() {
        Date date = new Date(1L);
        Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

        assertEquals("1234567890",
                (new BankTransaction(10L, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
                        .getSource());
    }

    @Test
    public void testGetTarget() {
        Date date = new Date(1L);
        Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

        assertEquals("1234567890",
                (new BankTransaction(10L, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
                        .getTarget());
    }

    @Test
    public void testExecuteTransaction() throws TransactionException {
        Date date = new Date(1L);
        Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

        BankTransaction bankTransaction = new BankTransaction(10L, date, sourceAcc,
                new Account(1234567890L, new Client("Dr Jane Doe"), 10L));
        bankTransaction.executeTransaction();
        assertEquals(
                "Transaction: | 70.01.01\t| Source: 1234567890\t| Target: 1234567890\t| Amount: 10\t| Balance: 20\t| Transaction"
                        + " state: EXECUTED\t|",
                bankTransaction.toString());
        assertEquals(Transaction.TransactionState.EXECUTED, bankTransaction.getTransactionState());
    }

    @Test
    public void testExecuteTransaction2() throws TransactionException {
        Date date = new Date(1L);
        Account sourceAcc = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

        thrown.expect(TransactionException.class);
        (new BankTransaction(Long.MAX_VALUE, date, sourceAcc, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)))
                .executeTransaction();
    }
}

