package org.TomasBarauskas.service;

import org.TomasBarauskas.excetions.NoRecordByID;
import org.TomasBarauskas.modul.ExpenseRecord;
import org.TomasBarauskas.modul.IncomeRecord;

import java.util.ArrayList;
import java.util.List;

public interface Budget {
    void addIncomeRecord(long amount, String info);

    void addExpenseRecord(long amount, String info);

    List<IncomeRecord> getIncomeRecords();

    List<ExpenseRecord> getExpenseRecords();

    long balance();

    void removeIncomeRecord(long incomeIdNumber) throws NoRecordByID;

    void removeExpenseRecord(long expenseIdNumber) throws NoRecordByID;

    ArrayList<Long> getIncomeRecordsID();

    ArrayList<Long> getExpenseRecordsID();
}
