package org.tomasBarauskas.service;

import org.tomasBarauskas.modul.FinanceRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FinanceRecordFileManager {
    void financeRecordFileWriter(List<FinanceRecord> financeRecords, String path, boolean append) throws IOException;

    List<FinanceRecord> getFinanceRecordFromFile(String path) throws IOException;

    void testSerializable(FinanceRecord financeRecord) throws IOException;

    List<FinanceRecord> testGetFromFile() throws IOException, ClassNotFoundException;
}
