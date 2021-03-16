package org.tomasBarauskas.service;

import org.tomasBarauskas.modul.ExpenseRecord;
import org.tomasBarauskas.modul.FinanceRecord;
import org.tomasBarauskas.modul.IncomeRecord;

import java.io.*;
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

    @Override
    public void testSerializable(FinanceRecord financeRecord) throws IOException {
        FileOutputStream fos = new FileOutputStream("/Users/Gabi/IdeaProjects/2021-03-10-biudzetas/src/main/java/org/tomasBarauskas/files/Test.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(financeRecord);
        oos.flush();
        oos.close();
    }

    // Pavel: Toliau del finansiniu irasu siulyciau txt nenaudoti, nes toki faila lengva pakoreguoti, nes neturi content checko kaip pvz netipizuoti failai kur informacija saugoma per serializacija.
    // Tomas: Jus toki irasymo buda turejot omenyje?
    @Override
    public List<FinanceRecord> testGetFromFile() throws IOException, ClassNotFoundException {
        List<FinanceRecord> records = new ArrayList<>();
        FileInputStream fis = new FileInputStream("/Users/Gabi/IdeaProjects/2021-03-10-biudzetas/src/main/java/org/tomasBarauskas/files/Test.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = null;

        try {
            while (!(obj = ois.read()).equals(null)) {   // Tomas: niekaip nerandu tinkamo budo while, kad nenulustu? Kas patarima galima?
                records.add((FinanceRecord) obj);
            }
        } catch (ClassCastException e){
            System.out.println("failo pabaiga");
        }
        return records;
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
