package org.tomasBarauskas.service;

import org.tomasBarauskas.excetions.NoRecordByID;
import org.tomasBarauskas.modul.ExpenseRecord;
import org.tomasBarauskas.modul.FinanceRecord;
import org.tomasBarauskas.modul.IncomeRecord;

import java.util.List;

public interface Budget {
    void addFinanceRecord(FinanceRecord financeRecord);

    List<IncomeRecord> getIncomeRecords();

    List<ExpenseRecord> getExpenseRecords();

    List<FinanceRecord> getFinanceRecords();

    float balance();

    void removeFinanceRecord(float FinanceIdNumber) throws NoRecordByID;

    List<Long> getIncomeRecordsID();

    List<Long> getExpenseRecordsID();

    List<Long> getFinanceRecordsID();

    FinanceRecord getFinanceRecordByID(float financeRedordID) throws NoRecordByID;
}
