package org.TomasBarauskas.modul;

public class ExpenseRecord extends FinanceRecord {
    private static final String EXPENSE_RECORD = "Islaidu irasas";

    public ExpenseRecord(long amount, String info) {
        super(amount, info);
    }

    @Override
    public String toString() {
        return "Islaidu " + super.toString();
    }
}
