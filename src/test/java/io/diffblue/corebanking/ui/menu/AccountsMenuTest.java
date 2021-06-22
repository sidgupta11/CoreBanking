package io.diffblue.corebanking.ui.menu;

import static org.junit.Assert.assertSame;

import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.datamanagement.ReadFromDB;
import io.diffblue.corebanking.transaction.TransactionException;
import org.junit.Test;

public class AccountsMenuTest {
    @Test
    public void testConstructor() throws TransactionException {
        CoreBanking readFromDBResult = ReadFromDB.readFromDB();
        assertSame((new AccountsMenu(readFromDBResult)).coreBanking, readFromDBResult);
    }

    @Test
    public void testExecuteMenuOption() throws TransactionException {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        (new AccountsMenu(ReadFromDB.readFromDB())).executeMenuOption(1);
    }

    @Test
    public void testExecuteMenuOption2() throws TransactionException {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        (new AccountsMenu(ReadFromDB.readFromDB())).executeMenuOption(0);
    }

    @Test
    public void testExecuteMenuOption3() throws TransactionException {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        (new AccountsMenu(ReadFromDB.readFromDB())).executeMenuOption(2);
    }

    @Test
    public void testExecuteMenuOption4() throws TransactionException {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        (new AccountsMenu(ReadFromDB.readFromDB())).executeMenuOption(-1);
    }
}

