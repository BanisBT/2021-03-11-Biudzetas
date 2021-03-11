package org.TomasBarauskas.modul;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExpenseRecord {
    private static long counter = 0;
    private long id;
    private long amount;
    private LocalDateTime dateTime;
    private String info;
    private static final String EXPENSE_RECORD = "Islaidu irasas";

    public ExpenseRecord() {
        this.id = counter++;
    }

    public ExpenseRecord(long amount, String info) {
        this();
        this.amount = amount;
        this.info = info;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return EXPENSE_RECORD + '\n' +
                "ID | Suma | Data | Info " + '\n' +
                id + " | " + amount + " | " + dateTime.format(dtf) + " | " + info + '\n';
    }

    public long getAmount() {
        return amount;
    }

    public long getId() {
        return id;
    }
}
