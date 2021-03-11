package org.TomasBarauskas.service;

import org.TomasBarauskas.modul.ExpenseRecord;
import org.TomasBarauskas.modul.IncomeRecord;

import java.util.List;

public interface Budget {
    void addIncomeRecord(long amount, String info);

    void addExpenseRecord(long amount, String info);

    List<IncomeRecord> getIncomeRecords();

    List<ExpenseRecord> getExpenseRecords();
}
