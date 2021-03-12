package org.TomasBarauskas.service;

import org.TomasBarauskas.excetions.NoRecordByID;
import org.TomasBarauskas.modul.ExpenseRecord;
import org.TomasBarauskas.modul.FinanceRecord;
import org.TomasBarauskas.modul.IncomeRecord;

import java.util.ArrayList;
import java.util.List;

public class BudgetImpl implements Budget {
    private List<FinanceRecord> financeRecords;

    public BudgetImpl() {
        financeRecords = new ArrayList<>();
    }

    @Override
    public void addFinanceRecord(FinanceRecord financeRecord) {
        financeRecords.add(financeRecord);
    }

    @Override
    public List<IncomeRecord> getIncomeRecords() {
        List<IncomeRecord> incomeRecords = new ArrayList<>();

        for (FinanceRecord record : financeRecords) {
            if (record instanceof IncomeRecord) {
                incomeRecords.add((IncomeRecord) record);
            }
        }
        return incomeRecords;
    }

    @Override
    public List<ExpenseRecord> getExpenseRecords() {
        List<ExpenseRecord> expenseRecords = new ArrayList<>();

        for (FinanceRecord record : financeRecords) {
            if (record instanceof ExpenseRecord) {
                expenseRecords.add((ExpenseRecord) record);
            }
        }
        return expenseRecords;
    }

    @Override
    public long balance() {
        List<IncomeRecord> incomeRecords = getIncomeRecords();
        List<ExpenseRecord> expenseRecords = getExpenseRecords();
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
    public void removeFinanceRecord(long financeIdNumber) throws NoRecordByID {
        FinanceRecord financeRecord = getFinanceRecordByID(financeIdNumber);
        financeRecords.remove(financeRecord);
    }

    @Override
    public ArrayList<Long> getIncomeRecordsID() {
        List<IncomeRecord> incomeRecords = getIncomeRecords();
        ArrayList<Long> incomesID = new ArrayList<>();

        for (FinanceRecord record : incomeRecords) {
            incomesID.add(record.getId());
        }
        return incomesID;
    }

    @Override
    public ArrayList<Long> getExpenseRecordsID() {
        List<ExpenseRecord> expenseRecords = getExpenseRecords();
        ArrayList<Long> expensesID = new ArrayList<>();

        for (FinanceRecord record : expenseRecords) {
            expensesID.add(record.getId());
        }
        return expensesID;
    }

    @Override
    public ArrayList<Long> getFinanceRecordsID() {
        ArrayList<Long> financeID = new ArrayList<>();

        for (FinanceRecord record : financeRecords) {
            financeID.add(record.getId());
        }
        return financeID;
    }

    public FinanceRecord getFinanceRecordByID(long financeIdNumber) throws NoRecordByID {
        for (FinanceRecord record : financeRecords) {
            if (record.getId() == financeIdNumber) {
                return record;
            }
        }
        throw new NoRecordByID();
    }
}