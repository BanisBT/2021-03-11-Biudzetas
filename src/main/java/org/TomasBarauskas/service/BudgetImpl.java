package org.TomasBarauskas.service;

import org.TomasBarauskas.excetions.NoRecordByID;
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

    @Override
    public long balance() {
        long balance = 0;
        for (IncomeRecord record : incomeRecords) {
            balance += record.getAmount();
        }
        for (ExpenseRecord record : expenseRecords) {
            balance -= record.getAmount();
        }
        return balance;
    }

    @Override
    public void removeIncomeRecord(long incomeIdNumber) throws NoRecordByID {
        IncomeRecord incomeRecordToDelete = doesIncomeRecordExist(incomeIdNumber);
        incomeRecords.remove(incomeRecordToDelete);
    }

    @Override
    public void removeExpenseRecord(long expenseIdNumber) throws NoRecordByID {
        ExpenseRecord expenseRecordToDelete = doesExpenseRecordExist(expenseIdNumber);
        expenseRecords.remove(expenseRecordToDelete);
    }

    @Override
    public ArrayList<Long> getIncomeRecordsID() {
        ArrayList<Long> incomesID = new ArrayList<>();
        for (IncomeRecord record : incomeRecords){
            incomesID.add(record.getId());
        }
        return incomesID;
    }

    @Override
    public ArrayList<Long> getExpenseRecordsID() {
        ArrayList<Long> expensesID = new ArrayList<>();
        for (ExpenseRecord record : expenseRecords){
            expensesID.add(record.getId());
        }
        return expensesID;
    }

    private IncomeRecord doesIncomeRecordExist(long incomeIdNumber) throws NoRecordByID {
        for (IncomeRecord record : incomeRecords) {
            if (record.getId() == incomeIdNumber) {
                return record;
            }
        }
        throw new NoRecordByID();
    }

    private ExpenseRecord doesExpenseRecordExist(long expenseIdNumber) throws NoRecordByID {
        for (ExpenseRecord record : expenseRecords) {
            if (record.getId() == expenseIdNumber) {
                return record;
            }
        }
        throw new NoRecordByID();
    }
}