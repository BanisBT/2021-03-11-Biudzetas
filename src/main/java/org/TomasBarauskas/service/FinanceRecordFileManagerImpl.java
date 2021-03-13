package org.TomasBarauskas.service;

import org.TomasBarauskas.modul.ExpenseRecord;
import org.TomasBarauskas.modul.FinanceRecord;
import org.TomasBarauskas.modul.IncomeRecord;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FinanceRecordFileManagerImpl implements FinanceRecordFileManager {
    @Override
    public void financeRecordFileWriter(List<FinanceRecord> financeRecords, String path, boolean append) throws IOException {
        PrintWriter pw = null;
        try {
            FileWriter fw = new FileWriter(path, append);
            BufferedWriter bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            for (FinanceRecord record : financeRecords) {
                pw.println(record.stringForPrintWriter());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null)
                pw.close();
        }
    }

    @Override
    public List<FinanceRecord> getFinanceRecordFromFile(String path) throws IOException {
        List<FinanceRecord> financeRecordsFromFile = new ArrayList<>();
        BufferedReader br = null;

        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis));
            String line;

            while ((line = br.readLine()) != null) {
                financeRecordsFromFile.add(financeRecordFromFile(line));
            }
        } finally {
            br.close();
        }
        return financeRecordsFromFile;
    }

    private FinanceRecord financeRecordFromFile(String line) {
        String[] textFromFile = line.split(",");
        FinanceRecord financeRecordFromFile = null;

        if (IncomeRecord.getIncomeRecord().equals(textFromFile[0])) {
            IncomeRecord tempIncomeRecord = new IncomeRecord();
            tempIncomeRecord.setId(Long.parseLong(textFromFile[1]));
            tempIncomeRecord.setAmount(Float.parseFloat(textFromFile[2]));
            tempIncomeRecord.setInfo(textFromFile[3]);
            tempIncomeRecord.setDateTime(LocalDateTime.parse(textFromFile[4]));
            financeRecordFromFile = tempIncomeRecord;

        } else if (ExpenseRecord.getExpenseRecord().equals(textFromFile[0])) {
            ExpenseRecord tempExpenseRecord = new ExpenseRecord();
            tempExpenseRecord.setId(Long.parseLong(textFromFile[1]));
            tempExpenseRecord.setAmount(Float.parseFloat(textFromFile[2]));
            tempExpenseRecord.setInfo(textFromFile[3]);
            tempExpenseRecord.setDateTime(LocalDateTime.parse(textFromFile[4]));
            financeRecordFromFile = tempExpenseRecord;
        }
        return financeRecordFromFile;
    }
}
