package org.TomasBarauskas.service;

import org.TomasBarauskas.modul.FinanceRecord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FinanceRecordFileManager {
    void financeRecordFileWriter(List<FinanceRecord> financeRecords, String path, boolean append) throws IOException;

    List<FinanceRecord> getFinanceRecordFromFile(String path) throws IOException;
}
