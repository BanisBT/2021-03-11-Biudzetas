package org.TomasBarauskas.modul;

public class IncomeRecord extends FinanceRecord {

    private static final String INCOME_RECORD = "Pajemu irasas";

    public IncomeRecord() {
    }

    public IncomeRecord(float amount, String info) {
        super(amount, info);
    }

    @Override
    public String toString() {
        return "Pajemu " + super.toString();
    }

    @Override
    public String stringForPrintWriter() {
        return String.format("%s,%s", INCOME_RECORD, super.stringForPrintWriter());
    }

    public static String getIncomeRecord() {
        return INCOME_RECORD;
    }

}
