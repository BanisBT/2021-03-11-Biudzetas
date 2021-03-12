package org.TomasBarauskas.service;

import org.TomasBarauskas.excetions.NoRecordByID;
import org.TomasBarauskas.modul.ExpenseRecord;
import org.TomasBarauskas.modul.FinanceRecord;
import org.TomasBarauskas.modul.IncomeRecord;

import java.util.ArrayList;
import java.util.List;

public interface Budget {
    void addFinanceRecord(FinanceRecord financeRecord);

    List<IncomeRecord> getIncomeRecords();

    List<ExpenseRecord> getExpenseRecords();

    long balance();

    void removeFinanceRecord(long FinanceIdNumber) throws NoRecordByID;

    ArrayList<Long> getIncomeRecordsID();

    ArrayList<Long> getExpenseRecordsID();

    ArrayList<Long> getFinanceRecordsID();

    FinanceRecord getFinanceRecordByID(long financeRedordID) throws NoRecordByID;
}
