package org.TomasBarauskas.service;

import org.TomasBarauskas.modul.ExpenseRecord;
import org.TomasBarauskas.modul.IncomeRecord;

import java.util.ArrayList;
import java.util.List;

public class BudgetImpl implements Budget {
    private List<IncomeRecord> incomeRecords;
    private List<ExpenseRecord> expenseRecords;

    public BudgetImpl() {
        incomeRecords = new ArrayList<>();
        expenseRecords = new ArrayList<>();
    }

    @Override
    public void addIncomeRecord(long amount, String info) {
        incomeRecords.add(new IncomeRecord(amount, info));
    }

    @Override
    public void addExpenseRecord(long amount, String info) {
        expenseRecords.add(new ExpenseRecord(amount, info));
    }

    @Override
    public List<IncomeRecord> getIncomeRecords() {
        return incomeRecords;
    }

    @Override
    public List<ExpenseRecord> getExpenseRecords() {
        return expenseRecords;
    }

}
