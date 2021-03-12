package org.TomasBarauskas.modul;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FinanceRecord {
    private static long counter = 0;
    private long id;
    private long amount;
    private String info;
    private LocalDateTime dateTime;

    public FinanceRecord() {
        this.id = counter++;
    }

    public FinanceRecord(long amount, String info) {
        this();
        this.amount = amount;
        this.info = info;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Financinis irasas" + '\n' +
                "ID | Suma | Info | Data-Laikas" + '\n'
                + id + " | " + amount + "Eur | " + info + " | " + dateTime.format(dtf) + '\n';
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        } else {
            FinanceRecord financeRecord = (FinanceRecord) obj;
            return id == financeRecord.getId();
        }
    }

    public long getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
