package io.diffblue.corebanking.transaction;

import static org.junit.Assert.assertEquals;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;

import java.util.Date;

import org.junit.Test;

public class CashTransactionTest {
    @Test
    public void testConstructor() {
        Date date = new Date(1L);
        CashTransaction actualCashTransaction = new CashTransaction(10L, date,
                new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

        assertEquals("CASH", actualCashTransaction.getSource());
        assertEquals(
                "Transaction: | 70.01.01\t| Source: CASH\t| Target: 1234567890\t| Amount: 10\t| Balance: 0\t| Transaction"
                        + " state: NOT_EXECUTED_YET\t|",
                actualCashTransaction.toString());
        assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
        assertEquals(10L, actualCashTransaction.getTransactionAmount());
        assertEquals("1234567890", actualCashTransaction.getTarget());
    }

    @Test
    public void testConstructor2() {
        Date date = new Date(1L);
        CashTransaction actualCashTransaction = new CashTransaction(-1L, date,
                new Account(1234567890L, new Client("Dr Jane Doe"), 10L));

        assertEquals("1234567890", actualCashTransaction.getSource());
        assertEquals(
                "Transaction: | 70.01.01\t| Source: 1234567890\t| Target: CASH\t| Amount: -1\t| Balance: 0\t| Transaction"
                        + " state: NOT_EXECUTED_YET\t|",
                actualCashTransaction.toString());
        assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
        assertEquals(-1L, actualCashTransaction.getTransactionAmount());
        assertEquals("CASH", actualCashTransaction.getTarget());
    }

    @Test
    public void testGetSource() {
        Date date = new Date(1L);
        assertEquals("CASH",
                (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).getSource());
    }

    @Test
    public void testGetSource2() {
        Date date = new Date(1L);
        assertEquals("1234567890",
                (new CashTransaction(-1L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).getSource());
    }

    @Test
    public void testGetTarget() {
        Date date = new Date(1L);
        assertEquals("1234567890",
                (new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).getTarget());
    }

    @Test
    public void testGetTarget2() {
        Date date = new Date(1L);
        assertEquals("CASH",
                (new CashTransaction(-1L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L))).getTarget());
    }

    @Test
    public void testExecuteTransaction() throws TransactionException {
        Date date = new Date(1L);
        CashTransaction cashTransaction = new CashTransaction(10L, date,
                new Account(1234567890L, new Client("Dr Jane Doe"), 10L));
        cashTransaction.executeTransaction();
        assertEquals(
                "Transaction: | 70.01.01\t| Source: CASH\t| Target: 1234567890\t| Amount: 10\t| Balance: 20\t| Transaction"
                        + " state: EXECUTED\t|",
                cashTransaction.toString());
        assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
    }

    @Test
    public void testExecuteTransaction2() throws TransactionException {
        Date date = new Date(1L);
        CashTransaction cashTransaction = new CashTransaction(-1L, date,
                new Account(1234567890L, new Client("Dr Jane Doe"), 10L));
        cashTransaction.executeTransaction();
        assertEquals(
                "Transaction: | 70.01.01\t| Source: 1234567890\t| Target: CASH\t| Amount: -1\t| Balance: 11\t| Transaction"
                        + " state: EXECUTED\t|",
                cashTransaction.toString());
        assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
    }

    @Test
    public void testExecuteTransaction3() throws TransactionException {
        Date date = new Date(1L);
        CashTransaction cashTransaction = new CashTransaction(Long.MIN_VALUE, date,
                new Account(1234567890L, new Client("Dr Jane Doe"), 10L));
        cashTransaction.executeTransaction();
        assertEquals(
                "Transaction: | 70.01.01\t| Source: 1234567890\t| Target: CASH\t| Amount: -9223372036854775808\t| Balance:"
                        + " 10\t| Transaction state: FAILED\t|",
                cashTransaction.toString());
        assertEquals(Transaction.TransactionState.FAILED, cashTransaction.getTransactionState());
    }
}

