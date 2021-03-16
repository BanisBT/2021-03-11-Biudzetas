package org.tomasBarauskas.modul;

public class ExpenseRecord extends FinanceRecord {
    private static final String EXPENSE_RECORD = "Islaidu irasas";

    public ExpenseRecord() {
    }

    public ExpenseRecord(float amount, String info) {
        super(amount, info);
    }

    @Override
    public String toString() {
        return "Islaidu " + super.toString();
    }

    @Override
    public String stringForPrintWriter() {
        return String.format("%s,%s",EXPENSE_RECORD,super.stringForPrintWriter());
    }

    public static String getExpenseRecord() {
        return EXPENSE_RECORD;
    }
}
