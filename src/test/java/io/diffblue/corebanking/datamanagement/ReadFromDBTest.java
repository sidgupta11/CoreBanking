package io.diffblue.corebanking.datamanagement;

import static org.junit.Assert.assertEquals;

import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.TransactionException;

import java.util.List;

import org.junit.Test;

public class ReadFromDBTest {
    @Test
    public void testReadFromDB() throws TransactionException {
        CoreBanking readFromDBResult = ReadFromDB.readFromDB();
        ReadFromDB.readFromDB(readFromDBResult);
        assertEquals(6, readFromDBResult.getAccounts().size());
        List<Client> clients = readFromDBResult.getClients();
        assertEquals(3, clients.size());
        Client getResult = clients.get(1);
        assertEquals("Jane Robbins", getResult.getClientName());
        assertEquals(2, getResult.getAccounts().size());
        Client getResult1 = clients.get(2);
        assertEquals("Emily Simmons", getResult1.getClientName());
        assertEquals(1, getResult1.getAccounts().size());
        Client getResult2 = clients.get(0);
        assertEquals("John Field", getResult2.getClientName());
        assertEquals(3, getResult2.getAccounts().size());
    }
}

