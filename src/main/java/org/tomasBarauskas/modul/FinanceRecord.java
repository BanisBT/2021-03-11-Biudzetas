package org.tomasBarauskas.modul;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FinanceRecord implements Serializable {
    private static long counter = 0;
    private long id;
    private float amount;
    private String info;
    private LocalDateTime dateTime;

    public FinanceRecord() {
        this.id = counter++;
    }

    public FinanceRecord(float amount, String info) {
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
    public String stringForPrintWriter(){
        return String.format("%d,%s,%s,%s",id,amount,info,dateTime);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
