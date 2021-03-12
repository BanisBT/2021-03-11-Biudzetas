package org.TomasBarauskas.modul;

public class IncomeRecord extends FinanceRecord {

    private static final String INCOME_RECORD = "Pajemu irasas";

    public IncomeRecord(long amount, String info) {
        super(amount, info);
    }

    @Override
    public String toString() {
        return "Pajemu " + super.toString();
    }
}
