package io.diffblue.corebanking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;

import java.util.List;

import org.junit.Test;


public class CoreBankingTest {
    @Test
    public void testConstructor() {
        CoreBanking actualCoreBanking = new CoreBanking();
        List<Account> expectedClients = actualCoreBanking.getAccounts();
        assertEquals(expectedClients, actualCoreBanking.getClients());
    }

    @Test
    public void testPurgeCoreBanking() throws TransactionException {
        CoreBanking readFromDBResult = ReadFromDB.readFromDB();
        readFromDBResult.purgeCoreBanking();
        assertTrue(readFromDBResult.getAccounts().isEmpty());
        assertEquals("", readFromDBResult.toString());
    }

    @Test
    public void testOpenNewAccount() throws TransactionException {
        CoreBanking readFromDBResult = ReadFromDB.readFromDB();
        Client client = new Client("Dr Jane Doe");
        Account actualOpenNewAccountResult = readFromDBResult.openNewAccount(client, 10L);
        assertEquals("Current", actualOpenNewAccountResult.getAccountName());
        assertEquals(10L, actualOpenNewAccountResult.getCurrentBalance());
        Client client1 = actualOpenNewAccountResult.getClient();
        assertSame(client, client1);
        assertEquals(Account.AccountState.OPEN, actualOpenNewAccountResult.getAccountState());
        assertTrue(actualOpenNewAccountResult.getAccountStatement().getTransactions().isEmpty());
        assertEquals(1, client1.getAccounts().size());
        assertEquals(7, readFromDBResult.getAccounts().size());
    }

    @Test
    public void testOpenNewAccount2() throws TransactionException {
        CoreBanking readFromDBResult = ReadFromDB.readFromDB();

        Client client = new Client("Dr Jane Doe");
        client.addAccount(new Account(1234567890L, new Client("Dr Jane Doe"), 10L));
        Account actualOpenNewAccountResult = readFromDBResult.openNewAccount(client, 10L);
        assertEquals("Current", actualOpenNewAccountResult.getAccountName());
        assertEquals(10L, actualOpenNewAccountResult.getCurrentBalance());
        Client client1 = actualOpenNewAccountResult.getClient();
        assertSame(client, client1);
        assertEquals(Account.AccountState.OPEN, actualOpenNewAccountResult.getAccountState());
        assertTrue(actualOpenNewAccountResult.getAccountStatement().getTransactions().isEmpty());
        assertEquals(2, client1.getAccounts().size());
        assertEquals(7, readFromDBResult.getAccounts().size());
    }

    @Test
    public void testRegisterNewClient() throws TransactionException {
        CoreBanking readFromDBResult = ReadFromDB.readFromDB();
        Client client = new Client("Dr Jane Doe");
        assertSame(client, readFromDBResult.registerNewClient(client));
        assertEquals(4, readFromDBResult.getClients().size());
    }

    @Test
    public void testToString() throws TransactionException {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        ReadFromDB.readFromDB().toString();
    }
}

