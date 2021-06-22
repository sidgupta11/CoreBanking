package io.diffblue.corebanking.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.CashTransaction;

import java.util.Date;
import java.util.Objects;

import org.junit.Rule;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testConstructor() {
        Client client = new Client("Dr Jane Doe");
        Account actualAccount = new Account(1234567890L, client, 10L);

        assertEquals("Current", actualAccount.getAccountName());
        assertEquals(1234567890L, actualAccount.getAccountNumber());
        assertEquals(Account.AccountState.OPEN, actualAccount.getAccountState());
        assertSame(client, actualAccount.getClient());
        assertEquals(10L, actualAccount.getCurrentBalance());
        assertEquals(
                "Account: | Acc #: 1234567890\t | Acc name: Current\t | Acc holder: Dr Jane Doe\t | Acc balance: 10\t | Acc"
                        + " state: OPEN\t |\n" + "Account statement empty.",
                actualAccount.toString());
    }

    @Test
    public void testConstructor2() {
        Account actualAccount = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

        assertEquals("Current", actualAccount.getAccountName());
        assertEquals(
                "Account: | Acc #: 1234567890\t | Acc name: Current\t | Acc holder: Dr Jane Doe\t | Acc balance: 10\t | Acc"
                        + " state: OPEN\t |\n" + "Account statement empty.",
                actualAccount.toString());
        assertEquals(10L, actualAccount.getCurrentBalance());
        assertEquals(1234567890L, actualAccount.getAccountNumber());
        assertEquals(Account.AccountState.OPEN, actualAccount.getAccountState());
        assertTrue(actualAccount.getAccountStatement().getTransactions().isEmpty());
    }

    @Test
    public void testAddToBalance() throws AccountException {
        Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
        account.addToBalance(10L);
        assertEquals(20L, account.getCurrentBalance());
    }

    @Test
    public void testTakeFromBalance() throws AccountException {
        Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
        account.takeFromBalance(10L);
        assertEquals(0L, account.getCurrentBalance());
    }

    @Test
    public void testTakeFromBalance2() throws AccountException {
        thrown.expect(AccountException.class);
        (new Account(1234567890L, new Client("Dr Jane Doe"), Long.MAX_VALUE)).takeFromBalance(10L);
    }

    @Test
    public void testSetAccountName() throws AccountException {
        Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
        account.setAccountName("Dr Jane Doe");
        assertEquals("Dr Jane Doe", account.getAccountName());
    }

    @Test
    public void testCloseAccount() throws AccountException {
        Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
        account.closeAccount();
        assertEquals(Account.AccountState.CLOSED, account.getAccountState());
    }

    @Test
    public void testAddTransaction() throws AccountException {
        Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
        Date date = new Date(1L);
        account.addTransaction(new CashTransaction(10L, date, new Account(1234567890L, new Client("Dr Jane Doe"), 10L)));
        assertEquals(1, account.getAccountStatement().getTransactions().size());
    }

    @Test
    public void testEquals() {
        assertFalse((new Account(1234567890L, new Client("Dr Jane Doe"), 10L)).equals(null));
        assertFalse((new Account(1234567890L, new Client("Dr Jane Doe"), 10L)).equals("Different type to Account"));
    }

    @Test
    public void testEquals2() {
        Account account = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);
        Account account1 = new Account(1234567890L, new Client("Dr Jane Doe"), 10L);

        assertTrue(account.equals(account1));
        int notExpectedHashCodeResult = account.hashCode();
        assertFalse(Objects.equals(notExpectedHashCodeResult, account1.hashCode()));
    }
}

